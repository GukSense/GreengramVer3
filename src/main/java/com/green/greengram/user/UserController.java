package com.green.greengram.user;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.user.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "유저", description = "유저 CRUD")
public class UserController {
    private final UserService service;


    @PostMapping("sign-up")
    public ResultDto<Integer> signUpPost(@RequestPart MultipartFile pic,  @RequestPart SignUpPostReq p) {
        int result = service.signUpPost(pic, p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("회원가입 성공")
                .resultData(result)
                .build();
    }

    @PostMapping("sign-in")
    public ResultDto<SignInRes> signInPost(@RequestBody SignInPostReq p) {

        SignInRes result = service.signInPost(p);

        return ResultDto.<SignInRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("로그인 성공")
                .resultData(result)
                .build();
    }


}
