package com.green.greengram.feed;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.feed.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
@Tag(name = "피드", description = "피드 CRUD")
public class FeedControllerImpl {
    private final FeedServiceImpl service;

    @PostMapping
    @Operation(summary = "FEED 등록", description = "")
    public ResultDto<FeedPostRes> postFeed(@RequestPart List<MultipartFile> pics, @RequestPart FeedPostReq p) {

        FeedPostRes result = service.postFeed(pics, p);

        return ResultDto.<FeedPostRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("피드등록 성공")
                .resultData(result)
                .build();
    }

    @GetMapping
    @Operation(summary = "피드리스트", description = "")
    public ResultDto<List<FeedGetRes>> getFeed(@ParameterObject @ModelAttribute FeedGetReq p) {
        log.info("p: {}",p);
        List<FeedGetRes> result = service.getFeed(p);

        log.info("p: {}",p);

        return ResultDto.<List<FeedGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result)
                .build();
    }
}
