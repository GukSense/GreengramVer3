package com.green.greengram.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignInRes {
    @Schema(example = "유저PK", description = "1")
    private long userId;
    @Schema(example = "유저닉네임", description = "쿠크다스커피맛")
    private String nm;
    @Schema(example = "프로필파일이름", description = "c450808c-48c")
    private String pic;

}
