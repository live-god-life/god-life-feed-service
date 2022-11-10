package com.godlife.feedservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.godlife.feedservice.domain.enums.Category;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedMindsetsTodosDto {
	//===피드 정보===
	private Long feedId;
	private Category category;
	private String title;

	//===카운팅===
	private int viewCount;
	private int pickCount;
	private int todoCount;
	private int todoScheduleDay;

	//===이미지===
	private String image;

	//===컨텐츠===
	private List<ContentDto> contents;

	//===마인드셋 정보===
	private List<MindsetDto> mindsets;

	//===투두 정보===
	private List<TodoDto> todos;

	@QueryProjection
	public FeedMindsetsTodosDto(Long feedId, Category category, String title, int viewCount, int pickCount, int todoCount, int todoScheduleDay, String image) {
		this.feedId = feedId;
		this.category = category;
		this.title = title;
		this.viewCount = viewCount;
		this.pickCount = pickCount;
		this.todoCount = todoCount;
		this.todoScheduleDay = todoScheduleDay;
		this.image = image;
	}

	public void registerContentDtos(List<ContentDto> contentDtos) {
		this.contents = contentDtos;
	}

	public void registerMindsetDtos(List<MindsetDto> mindsetDtos) {
		this.mindsets = mindsetDtos;
	}

	public void registerTodoDtos(List<TodoDto> todoDtos) {
		this.todos = todoDtos;
	}
}
