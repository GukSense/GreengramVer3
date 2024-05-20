package com.green.greengram.userfollow.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserFollowReq {
    @Schema(example = "15", description = "팔로워 유저 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private long  fromUserId;
    @Schema(example = "17", description = "팔로잉 유저 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private long toUserId;

}
