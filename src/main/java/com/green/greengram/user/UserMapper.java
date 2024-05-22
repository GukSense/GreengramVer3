package com.green.greengram.user;

import com.green.greengram.user.model.*;
import com.green.greengram.userfollow.model.UserInfoGetReq;
import com.green.greengram.userfollow.model.UserInfoGetRes;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    int signUpPost(SignUpPostReq p);
    User getUserById(String p);

    SignInRes signInPost(SignInPostReq p);

    UserInfoGetRes selProfileUserInfo(UserInfoGetReq p);
    int updProfilePic(UserProfilePatchReq p);
}
