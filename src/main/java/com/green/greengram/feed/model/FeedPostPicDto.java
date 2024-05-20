package com.green.greengram.feed.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class FeedPostPicDto {
    private long feedId;
    @Builder.Default
    private List<String> fileNames = new ArrayList<>();
}
