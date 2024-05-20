package com.green.greengram.feed;

import com.green.greengram.common.GlobalConst;
import com.green.greengram.common.model.CustomFileUtils;
import com.green.greengram.feed.model.*;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final CustomFileUtils customFileUtils;

    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p) {
        int result = mapper.postFeed(p);

            FeedPostPicDto postPicDto = FeedPostPicDto.builder().feedId(p.getFeedId()).build();
            try {
                String path = String.format("feed/%d", p.getFeedId());
                customFileUtils.makeFolderName(path);
                for (MultipartFile pic : pics) {
                    String saveFileName = customFileUtils.makeRandomFileName(pic);
                    postPicDto.getFileNames().add(saveFileName);
                    String target = String.format("%s/%s", path, saveFileName);
                    customFileUtils.transferTo(pic, target);

                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("피드등록 오류");
            }

         mapper.postFeedPicDto(postPicDto);

        log.info("{}",p);
        return FeedPostRes.builder()
                .feedId(p.getFeedId())
                .pics(postPicDto.getFileNames())
                .build();
    }

    public List<FeedGetRes> getFeed(FeedGetReq p) {
        List<FeedGetRes> list = mapper.getFeed(p);
// int inputData = /10
        for ( FeedGetRes res : list) {
            List<String> pics = mapper.getFeedPicsByFeedId(res.getFeedId());
            res.setPics(pics);
            // 댓글리스트
            List<FeedCommentGetRes> comments = mapper.getFeedCommentTopBy4ByFeedId(res.getFeedId());
            if (comments.size() == 4) {
                res.setIsMoreComment(1);
                comments.remove(GlobalConst.COMMENT_PAGING_SIZE - 1);
            }
            res.setComments(comments);
        }

        return list;
    }
}
