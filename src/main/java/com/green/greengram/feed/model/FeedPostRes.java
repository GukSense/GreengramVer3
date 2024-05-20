package com.green.greengram.feed.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class FeedPostRes {
    private long feedId;
    private List<String> pics;
}
