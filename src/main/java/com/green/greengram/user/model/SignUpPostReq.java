package com.green.greengram.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignUpPostReq {
    @JsonIgnore
    private long userId;
    @JsonIgnore
    private String pic; // 프로필 사진 이름
    @Schema(example = "유저아이디", description = "abc1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uid;
    @Schema(example = "유저비밀번호", description = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
    @Schema(example = "유저닉네임", description = "쿠크다스커피맛존맛탱", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nm;

}
