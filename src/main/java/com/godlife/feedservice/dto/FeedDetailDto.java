package com.godlife.feedservice.dto;

import java.util.List;

import lombok.Getter;

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
}
