package com.godlife.feedservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class FeedsDto {
	private Long feedId;
	private String title;

	//===카운팅===
	private int viewCount;
	private int pickCount;
	private int todoCount;
	private int todoScheduleCount;

	//===이미지===
	private String image;

	//===북마크정보===
	private boolean bookMarkStatus;

	//===사용자정보===
	@JsonIgnore
	private Long userId;
	private UserDto user;

	@QueryProjection
	public FeedsDto(Long feedId, String title, int viewCount, int pickCount, int todoCount, int todoScheduleCount, String image, Long userId) {
		this.feedId = feedId;
		this.title = title;
		this.viewCount = viewCount;
		this.pickCount = pickCount;
		this.todoCount = todoCount;
		this.todoScheduleCount = todoScheduleCount;
		this.image = image;
		this.userId = userId;
	}

	public void registerUser(UserDto userDto) {
		this.user = userDto;
	}
}
