package com.godlife.feedservice.api.request;

import java.util.List;
import java.util.stream.Collectors;

import com.godlife.feedservice.domain.Feed;
import com.godlife.feedservice.domain.Mindset;
import com.godlife.feedservice.domain.Todo;
import com.godlife.feedservice.domain.TodoFolder;
import com.godlife.feedservice.domain.TodoTask;
import com.godlife.feedservice.domain.enums.Category;
import com.godlife.feedservice.domain.enums.RepetitionType;
import com.godlife.feedservice.domain.enums.TodoType;

import lombok.Getter;

@Getter
public class CreateFeedRequest {
	private Long userId;

	private String title;
	private String content;
	private String image;

	private String categoryName;
	private String categoryCode;

	private List<CreateFeedRequest.CreateGoalMindsetRequest> mindsets;
	private List<CreateFeedRequest.CreateGoalTodoRequest> todos;

	public Feed createFeedEntity() {
		return Feed.createFeed(userId, Category.valueOf(categoryCode), title, content, image);
	}

	public List<Mindset> createMindsetsEntity(Feed feed) {
		return mindsets.stream()
			.map(createFeedMindsetRequest -> Mindset.createMindset(createFeedMindsetRequest.getContent(), feed))
			.collect(Collectors.toList());
	}

	public List<Todo> createTodosEntity(Feed feed) {
		return todos.stream()
			.map(todoDto -> createTodo(todoDto, feed))
			.collect(Collectors.toList());
	}

	private Todo createTodo(CreateFeedRequest.CreateGoalTodoRequest todoDto, Feed feed) {
		if (TodoType.FOLDER.name().equals(todoDto.getType())) {
			return TodoFolder.createTodoFolder(
				todoDto.getTitle(),
				todoDto.getDepth(),
				todoDto.getOrderNumber(),
				todoDto.getTodos()
					.stream()
					.map(createFeedTodoRequest -> createTodo(createFeedTodoRequest, feed))
					.collect(Collectors.toList()),
				feed);
		} else {
			return TodoTask.createTodoTask(
				todoDto.getTitle(),
				todoDto.getDepth(),
				todoDto.getOrderNumber(),
				RepetitionType.valueOf(todoDto.getRepetitionType()),
				todoDto.getRepetitionParams(),
				todoDto.getNotification(),
				feed);
		}
	}

	@Getter
	public static class CreateGoalMindsetRequest {
		private String content;
	}

	@Getter
	public static class CreateGoalTodoRequest {
		private String title;
		private String type;
		private Integer depth;
		private Integer orderNumber;
		private String startDate;
		private String endDate;
		private String repetitionType;
		private List<String> repetitionParams;
		private String notification;
		private List<CreateGoalTodoRequest> todos;
	}
}