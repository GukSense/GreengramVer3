package com.green.greengram.feedcomment.model;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Setter
@Getter

public class FeedCommentDelReq {
    private long signedUserId;
    private long feedCommentId;

    @ConstructorProperties({"feed_comment_id", "signed_user_id"})
    public FeedCommentDelReq(long feedCommentId, long signedUserId)  {
        this.feedCommentId = feedCommentId;
        this.signedUserId = signedUserId;
    }
}
