package com.green.greengram.feedcomment;

import com.green.greengram.common.GlobalConst;
import com.green.greengram.feed.FeedMapper;
import com.green.greengram.feedcomment.model.FeedCommentDelReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedCommentService {
    private final FeedCommentMapper mapper;

    public long feedCommentPost(FeedCommentPostReq p) {
        mapper.feedCommentPost(p);

        return p.getFeedCommentId();
    }

    public List<FeedCommentGetRes> getFeedComment(long feedId) {
        List<FeedCommentGetRes> feedComment = mapper.getFeedComment(feedId);

        feedComment.subList(0, GlobalConst.COMMENT_PAGING_SIZE -1).clear();

        return feedComment;
    }

    public int delFeedComment(FeedCommentDelReq p) {
        return mapper.delFeedComment(p);
    }
}
