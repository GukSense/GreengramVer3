package com.green.greengram.userfollow.model;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;


@Getter
public class UserInfoGetReq {
    private long signedUserId;
    private long profileUserId;

    @ConstructorProperties({"signed_user_id", "profile_user_id"})
    public UserInfoGetReq(long signedUserId, long profileUserId) {
        this.signedUserId = signedUserId;
        this.profileUserId = profileUserId;
    }
}
