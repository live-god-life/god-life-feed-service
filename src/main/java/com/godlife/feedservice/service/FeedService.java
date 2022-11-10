package com.godlife.feedservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godlife.feedservice.api.request.CreateFeedRequest;
import com.godlife.feedservice.domain.Feed;
import com.godlife.feedservice.domain.Content;
import com.godlife.feedservice.domain.Mindset;
import com.godlife.feedservice.domain.Todos;
import com.godlife.feedservice.dto.FeedMindsetsTodosDto;
import com.godlife.feedservice.dto.FeedsDto;
import com.godlife.feedservice.dto.UserDto;
import com.godlife.feedservice.repository.ContentRepository;
import com.godlife.feedservice.repository.FeedRepository;
import com.godlife.feedservice.repository.MindsetRepository;
import com.godlife.feedservice.repository.TodoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedService {
	private final FeedRepository feedRepository;
	private final ContentRepository contentRepository;
	private final MindsetRepository mindsetRepository;
	private final TodoRepository todoRepository;

	public List<FeedsDto> getFeeds(Pageable page, @Nullable String category, @Nullable List<Long> feedIds) {

		List<FeedsDto> feedsDtos = feedRepository.findAllByCategoryAndFeedIds(page, category, feedIds);
		feedDtosSetUserInfo(feedsDtos, getUsersInfoUsingAPI(feedsDtos));
		return feedsDtos;
	}

	private void feedDtosSetUserInfo(List<FeedsDto> feedsDtos, List<UserDto> userDtos) {
		userDtos.forEach(userDto -> feedsDtos.stream()
			.filter(feedsDto -> feedsDto.getUserId().equals(userDto.getUserId()))
			.collect(Collectors.toList())
			.forEach(feedsDto -> feedsDto.registerUser(userDto)));
	}

	@Transactional
	public FeedMindsetsTodosDto getFeedDetail(Long feedId) {
		return feedRepository.findFeedWithMindsetsAndTodosByFeedId(feedId);
	}

	//TODO user 정보 API를 통해 받아오기 (GET /users/profile?ids=1,2,3..)
	private List<UserDto> getUsersInfoUsingAPI(List<FeedsDto> feeds) {
		String userIds = feeds.stream()
			.map(feed -> feed.getUserId().toString())
			.collect(Collectors.joining(","));

		return List.of(new UserDto(1L, "닉네임1", "https://server/1.jpg"), new UserDto(2L, "닉네임2", "https://server/2.jpg"));
	}

	@Transactional
	public void createFeed(CreateFeedRequest feedDto) {
		Feed feed = feedDto.createFeedEntity();
		List<Content> contents = feedDto.createContentsEntity(feed);
		List<Mindset> mindsets = feedDto.createMindsetsEntity(feed);
		Todos todos = new Todos(feedDto.createTodosEntity(feed));

		feed.registerTodosInfo(todos);

		contentRepository.saveAll(contents);
		mindsetRepository.saveAll(mindsets);
		todoRepository.saveAll(todos.get());
	}
}
