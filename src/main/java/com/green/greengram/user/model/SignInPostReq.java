package com.green.greengram.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignInPostReq {
    @JsonIgnore
    private long userId;
    @Schema(example = "유저아이디", description = "abc1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uid;
    @Schema(example = "유저비밀번호", description = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
}
