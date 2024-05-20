package com.green.greengram.feedfavorite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FeedFavoriteToggleReq {
    private long feedId;
    private long userId;


}
