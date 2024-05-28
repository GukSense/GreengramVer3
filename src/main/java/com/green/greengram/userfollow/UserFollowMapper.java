package com.green.greengram.userfollow;

import com.green.greengram.userfollow.model.UserFollowEntity;
import com.green.greengram.userfollow.model.UserFollowReq;
import com.green.greengram.userfollow.model.UserInfoGetReq;
import com.green.greengram.userfollow.model.UserInfoGetRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserFollowMapper {
    int insUserFollow(UserFollowReq p);

    int deleteUserFollow(UserFollowReq p);

    List<UserFollowEntity> selUserFollowForTest(UserFollowReq p);
}
