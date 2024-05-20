package com.green.greengram.email;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.email.model.EmailMessage;
import com.green.greengram.email.model.EmailPostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/send-amil")
public class EmailController {
    private final EmailService service;

    @PostMapping("password")
    public ResultDto<String> sendPasswordMail(@RequestBody EmailPostDto p) {
        EmailMessage emailMessage = EmailMessage.builder()
                .to(p.getEmail())
                .subject("[Green Gram] 임시 비밀번호 발급")
                .build();

        String result = service.sendMail(emailMessage, "password");

        return ResultDto.<String>builder()
                        .statusCode(HttpStatus.OK)
                        .resultMsg("임시비밀번호 발송 완료")
                        .resultData(result).build();

    }
}
