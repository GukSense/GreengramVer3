package com.green.greengram.userfollow.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;


@Getter
public class UserInfoGetReq {
    @Schema(name = "signed_user_id", defaultValue = "15", description = "로그인한 사용자")
    private long signedUserId;
    @Schema(name = "profile_user_id", defaultValue = "17", description = "프로필 사용자")
    private Long profileUserId;

    @ConstructorProperties({"signed_user_id", "profile_user_id"})
    public UserInfoGetReq(long signedUserId, Long profileUserId) {
        this.signedUserId = signedUserId;
        this.profileUserId = profileUserId;
    }
}
