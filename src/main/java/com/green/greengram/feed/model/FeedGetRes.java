package com.green.greengram.feed.model;

import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString
public class FeedGetRes {
    // 작성자pk , 작성자nm  작성자사진, 로케이션, 피드사진들, 작성시간, 코멘트리스트, 좋아요처리를 위한 유저,
    // 피드pk, 콘텐츠, 좋아요여브
    private long feedId;

    private long writerId;
    private String writerNm;
    private String writerPic;
    private String location;
    private String contents;
    private int isFav;
    private String createdAt;


    private List<String> pics;
    private List<FeedCommentGetRes> comments;
    private int isMoreComment;





}
