package com.green.greengram.email;

import com.green.greengram.common.GlobalConst;
import com.green.greengram.email.model.EmailMessage;
import com.green.greengram.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final UserService service;
    private final SpringTemplateEngine springTemplateEngine;

    public String sendMail(EmailMessage message, String type) {
        //From 설정, authNum 가져오기

        String from = GlobalConst.FROM;
        String authNum = createdCode();
        if (type.equals("password")) {
            //유저비밀번호 변경
        }
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            messageHelper.addTo(message.getTo());
            messageHelper.setSubject(message.getSubject());
            messageHelper.setFrom(from);
            messageHelper.setText(setContext(authNum, type), true);

            javaMailSender.send(mimeMessage);

            log.info("Success!");
        } catch (MessagingException e) {
            log.info("fail");
            throw new RuntimeException(e);
        }

        return authNum;
    }

    private String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return springTemplateEngine.process(type, context);
    }

    private String createdCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {

            int  index = random.nextInt(4);

            switch (index) {
                case 0:
                    char a = (char)(random.nextInt(26) + 97) ;
                    key.append(a); // a~z
                    System.out.println(a);
                    break;
                case 1:
                    a = (char)(random.nextInt(26) + 65) ;
                    key.append(a);// A~Z
                    System.out.println(a);
                    break;
                default:
                    int v = random.nextInt(10) ;
                    key.append(v);
                    System.out.println(v);
                    log.info("{}",key);
            }
        }
        log.info("{}",key);

        return key.toString();

    }


}
