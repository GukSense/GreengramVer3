package com.green.greengram.common.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class CustomFileUtils {
    @Value("${file.directory}")
    public final String uploadPath;

    public CustomFileUtils(@Value("${file.directory}")String uploadPath) {
        this.uploadPath = uploadPath;
    }

    //폴더생성
    public String makeFolderName(String path) {
        File saveFolder = new File(uploadPath, path);
        saveFolder.mkdirs();
        return saveFolder.getAbsolutePath();
    }

    //랜덤 파일명 생성
    public String makeRandomFileName() {
        return UUID.randomUUID().toString();
    }

    // 확장자추출
    public String getExt(String fileName) {
        int idx = fileName.lastIndexOf(".");
        return fileName.substring(idx);
    }

    public String makeRandomFileName(String fileName) {
        return makeRandomFileName() + getExt(fileName);
    }
    public String makeRandomFileName(MultipartFile mf) {
        return mf == null || mf.isEmpty() ? null : makeRandomFileName(mf.getOriginalFilename());
    }

    //파일 전송
    public void transferTo(MultipartFile mf, String target) throws IOException {
        File file = new File(uploadPath, target);
        mf.transferTo(file);
    }

    public void deleteFolder(String absoluteFolderPath) {
        File folder = new File(absoluteFolderPath); //folderPath -> 상대주소
        if (folder.exists() && folder.isDirectory()) { //파일이 존재하면 true 존재하지않으면 false && folder 객체가 폴더인지 파일인지 구분하기 위한 메소드
            File[] files = folder.listFiles(); // 지정 디렉토리의 파일 내용을 가져온다 만약 디렉토리가 아니면 null 반환

            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f.getAbsolutePath());
                } else {
                    f.delete();
                }
            }
            folder.delete();

        }
    }

}
