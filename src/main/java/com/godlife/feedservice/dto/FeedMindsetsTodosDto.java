package com.godlife.feedservice.dto;

import java.util.List;

import com.godlife.feedservice.domain.enums.Category;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class FeedMindsetsTodosDto {
	//===피드 정보===
	private Long feedId;
	private Category category;
	private String title;
	private String content;

	//===카운팅===
	private int viewCount;
	private int pickCount;
	private int todoCount;
	private int todoScheduleDay;

	//===이미지===
	private String image;

	//===마인드셋 정보===
	private List<MindsetDto> mindsets;

	//===투두 정보===
	private List<TodoDto> todos;

	@QueryProjection
	public FeedMindsetsTodosDto(Long feedId, Category category, String title, String content, int viewCount, int pickCount, int todoCount, int todoScheduleDay, String image) {
		this.feedId = feedId;
		this.category = category;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.pickCount = pickCount;
		this.todoCount = todoCount;
		this.todoScheduleDay = todoScheduleDay;
		this.image = image;
	}

	public void registerMindsetDtos(List<MindsetDto> mindsetDtos) {
		this.mindsets = mindsetDtos;
	}

	public void registerTodoDtos(List<TodoDto> todoDtos) {
		this.todos = todoDtos;
	}
}
