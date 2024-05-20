package com.green.greengram.userfollow;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.userfollow.model.UserFollowReq;
import com.green.greengram.userfollow.model.UserInfoGetReq;
import com.green.greengram.userfollow.model.UserInfoGetRes;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/follow")
@Tag(name = "팔로우",description = "")
public class UserFollowController {
    private final UserFollowService service;

    @PostMapping
    public ResultDto<Integer> postUserFollow(@RequestBody UserFollowReq p) {
        int result = service.postUserFollow(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build();
    }

    @DeleteMapping
    public ResultDto<Integer> deleteUserFollow(@ParameterObject @ModelAttribute UserFollowReq p) {
        int result = service.deleteUserFollow(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build();
    }

    @GetMapping
    public ResultDto<UserInfoGetRes> selProfileUserInfo(@ParameterObject @ModelAttribute UserInfoGetReq p) {
        UserInfoGetRes result = service.selProfileUserInfo(p);

        return ResultDto.<UserInfoGetRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("유저 프로필 정보")
                .resultData(result)
                .build();
    }

}
