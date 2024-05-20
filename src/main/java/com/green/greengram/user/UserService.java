package com.green.greengram.user;

import com.green.greengram.common.model.CustomFileUtils;
import com.green.greengram.user.model.SignInPostReq;
import com.green.greengram.user.model.SignInRes;
import com.green.greengram.user.model.SignUpPostReq;
import com.green.greengram.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

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
}
