package com.green.greengram.feedcomment.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedCommentGetRes {
    private String feedCommentId;
    private long writerId;
    private String writerNm;
    private String comment;
    private String writerPic;
    private String createdAt;
}
