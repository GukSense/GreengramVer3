package com.green.greengram.feed.model;

import com.green.greengram.common.GlobalConst;
import com.green.greengram.common.model.Paging;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
@ToString
public class FeedGetReq extends Paging {


    private long signedUserId;  // maybe 좋아요처리
    public FeedGetReq(Integer size, Integer page, @BindParam("signed_user_id") long signedUserId) {
        super(size == null ? GlobalConst.FEED_PAGING_SIZE : size, page);
        this.signedUserId = signedUserId;
    }
}
