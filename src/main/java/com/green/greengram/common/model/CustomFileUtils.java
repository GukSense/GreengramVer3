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
    private String uploadPath;

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

}
