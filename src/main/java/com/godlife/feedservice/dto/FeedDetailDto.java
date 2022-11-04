package com.godlife.feedservice.dto;

import com.godlife.feedservice.domain.Feed;
import com.godlife.feedservice.domain.Mindset;
import com.godlife.feedservice.domain.Todo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class FeedDetailDto {
    private Long feedId;
    private String title;
    private String category;
    private String content;

    //===카운팅===
    private int viewCount;
    private int pickCount;

    //===이미지===
    private String image;

    //===사용자정보===
    private Long userId;
    private UserDto user;
    private boolean bookMarkStatus;

    //===마인드셋===
    private List<MindsetDto> mindsets;
    //===투두===
    private List<TodoDto> todos;

    public static FeedDetailDto createDtoWithFeedAndMindsetsAndTodos(Feed feed, List<Mindset> mindsets, List<Todo> todos) {
        return FeedDetailDto.builder()
                .feedId(feed.getFeedId())
                .title(feed.getTitle())
                .category(feed.getCategory())
                .content(feed.getContent())
                .viewCount(feed.getViewCount())
                .pickCount(feed.getPickCount())
                .image(feed.getImage())
                .userId(feed.getUserId())
                .mindsets(mindsets.stream().map(MindsetDto::of).collect(Collectors.toList()))
                .todos(todos.stream().map(TodoDto::of).collect(Collectors.toList()))
                .build();
    }
}
