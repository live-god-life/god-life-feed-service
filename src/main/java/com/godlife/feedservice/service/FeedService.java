package com.godlife.feedservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godlife.feedservice.api.request.CreateFeedRequest;
import com.godlife.feedservice.client.UserServiceClient;
import com.godlife.feedservice.client.response.BookmarkResponse;
import com.godlife.feedservice.domain.Feed;
import com.godlife.feedservice.domain.Content;
import com.godlife.feedservice.domain.Mindset;
import com.godlife.feedservice.domain.Todos;
import com.godlife.feedservice.dto.FeedMindsetsTodosDto;
import com.godlife.feedservice.dto.FeedsDto;
import com.godlife.feedservice.client.response.UserResponse;
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

	private final UserServiceClient userServiceClient;

	public List<FeedsDto> getFeeds(Pageable page, Long userId, @Nullable String category, @Nullable List<Long> feedIds) {

		List<FeedsDto> feedsDtos = feedRepository.findAllByCategoryAndFeedIds(page, category, feedIds);
		feedDtosSetUserInfo(feedsDtos, getUsersInfoUsingAPI(feedsDtos));
		feedDtosSetBookmarkInfo(feedsDtos, getBookmarksInfoUsingAPI(userId, feedsDtos));
		return feedsDtos;
	}

	private void feedDtosSetBookmarkInfo(List<FeedsDto> feedsDtos, List<BookmarkResponse.BookmarkDto> bookmarkDtos) {
		bookmarkDtos.forEach(
			bookmarkDto -> feedsDtos.stream()
				.filter(feedsDto -> feedsDto.getFeedId().equals(bookmarkDto.getFeedId()))
				.findFirst()
				.get()
				.registerBookmark(bookmarkDto.isBookmarkStatus())
		);
	}

	private void feedDtosSetUserInfo(List<FeedsDto> feedsDtos, List<UserResponse.UserDto> userDtos) {
		userDtos.forEach(userDto -> feedsDtos.stream()
			.filter(feedsDto -> feedsDto.getUserId().equals(userDto.getUserId()))
			.collect(Collectors.toList())
			.forEach(feedsDto -> feedsDto.registerUser(userDto)));
	}

	private List<UserResponse.UserDto> getUsersInfoUsingAPI(List<FeedsDto> feeds) {
		String ids = feeds.stream()
			.map(feed -> feed.getUserId().toString())
			.collect(Collectors.joining(","));
		return userServiceClient.getUsers(ids).getData();
	}

	private List<BookmarkResponse.BookmarkDto> getBookmarksInfoUsingAPI(Long userId, List<FeedsDto> feeds) {
		String ids = feeds.stream()
			.map(feed -> feed.getFeedId().toString())
			.collect(Collectors.joining(","));
		return userServiceClient.getBookmarks(userId, ids).getData();
	}

	@Transactional
	public FeedMindsetsTodosDto getFeedDetail(Long feedId) {
		return feedRepository.findFeedWithMindsetsAndTodosByFeedId(feedId);
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
