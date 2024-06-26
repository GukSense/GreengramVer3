package com.green.greengram.user;

import com.green.greengram.common.model.CustomFileUtils;
import com.green.greengram.user.model.*;
import com.green.greengram.userfollow.model.UserInfoGetReq;
import com.green.greengram.userfollow.model.UserInfoGetRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final CustomFileUtils customFileUtils;

    @Transactional
    public int signUpPost(MultipartFile pic, SignUpPostReq p) {
        //비밀번호 해싱
        String hashpwed = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        String saveFileName = customFileUtils.makeRandomFileName(pic);

        p.setUpw(hashpwed);
        p.setPic(saveFileName);

        int result = mapper.signUpPost(p);
        if (pic == null ) {return result;}

        try {
            String path = String.format("user/%s", p.getUserId());
            customFileUtils.makeFolderName(path);
            String target = String.format("%s/%s", path, saveFileName);
            customFileUtils.transferTo(pic, target);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("프로필 사진 업로드 오류");
        }

        return result;
    }

    public SignInRes signInPost(@RequestBody SignInPostReq p) {
        User user = mapper.getUserById(p.getUid()); // DB get User Info

        if (user == null) {
            throw new RuntimeException("아이디를 확인해주세요.");
        }
        if (!BCrypt.checkpw(p.getUpw(), user.getUpw())) {
            throw new RuntimeException("비밀번호를 확인해주세요.");
        }

        return mapper.signInPost(p);
    }

    public UserInfoGetRes getUserInfo(UserInfoGetReq p) {
        return mapper.selProfileUserInfo(p);
    }
    @Transactional
    public String patchProfilePic(UserProfilePatchReq p){
//        log.info("{}", p.getPic().getName());
        String fileName = customFileUtils.makeRandomFileName(p.getPic()); // 파일명 랜덤변환
        p.setPicName(fileName); // 랜덤파일명 객체에 저장
        mapper.updProfilePic(p); // DB 유저 프로필 파일명 수정

        //기존 폴더 삭제
        try {
        String midPath = String.format("user/%d",p.getSignedUserId()); //변경 유저의 프로필사진 파일이 저장되어있는경로
        String delAbsolutePath = String.format("%s%s", customFileUtils.uploadPath, midPath);
        customFileUtils.deleteFolder(delAbsolutePath); // 폴더 삭제, 파일 삭제

        customFileUtils.makeFolderName(midPath); // 경로 폴더 생성
        String filePath = String.format("%s/%s", midPath, fileName);

        customFileUtils.transferTo(p.getPic(), filePath); //경로에 파일 전송
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileName;
    }
}
