package com.green.greengram.feedcomment;

import com.green.greengram.feedcomment.model.FeedCommentDelReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int feedCommentPost(FeedCommentPostReq p);

    List<FeedCommentGetRes> getFeedComment(long feedId);

    int delFeedComment(FeedCommentDelReq p);

}
