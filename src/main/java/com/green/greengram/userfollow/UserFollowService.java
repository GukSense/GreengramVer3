package com.green.greengram.userfollow;

import com.green.greengram.userfollow.model.UserFollowReq;
import com.green.greengram.userfollow.model.UserInfoGetReq;
import com.green.greengram.userfollow.model.UserInfoGetRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFollowService {
    private final UserFollowMapper mapper;

    public int postUserFollow(UserFollowReq p) {
        return mapper.insUserFollow(p);
    }

    public int deleteUserFollow(UserFollowReq p) {
        return mapper.deleteUserFollow(p);
    }

    public UserInfoGetRes selProfileUserInfo(UserInfoGetReq p) {
        return mapper.selProfileUserInfo(p);
    }
}
