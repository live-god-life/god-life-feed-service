package com.godlife.feedservice.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godlife.feedservice.domain.Feed;
import com.godlife.feedservice.domain.Mindset;
import com.godlife.feedservice.domain.Todo;
import com.godlife.feedservice.dto.FeedDetailDto;
import com.godlife.feedservice.dto.FeedsDto;
import com.godlife.feedservice.dto.UserDto;
import com.godlife.feedservice.exception.NoSuchFeedException;
import com.godlife.feedservice.repository.FeedRepository;
import com.godlife.feedservice.repository.MindsetRepository;
import com.godlife.feedservice.repository.TodoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedService {
	private final FeedRepository feedRepository;
	private final MindsetRepository mindsetRepository;
	private final TodoRepository todoRepository;

	public List<FeedsDto> getFeeds(Pageable page, String category, List<Long> ids) {
		List<Feed> feeds;
		if (Objects.nonNull(ids)) {
			feeds = feedRepository.findByIds(page, ids);
		} else {
			feeds = category.equals("ALL") ? feedRepository.findAll(page).getContent() : feedRepository.findAllByCategory(page, category);
		}
		return createFeedDtos(feeds, getUsersInfo(feeds));
	}

	@Transactional
	public FeedDetailDto getFeedById(Long feedId) {
		Feed feed = feedRepository.findById(feedId)
			.orElseThrow(() -> new NoSuchFeedException(feedId));
		feed.plusViewCount();
		List<Mindset> mindsets = mindsetRepository.findByFeed(feed);
		List<Todo> todos = todoRepository.findByFeedAndDepth(feed, 1);

		return FeedDetailDto.createDtoWithFeedAndMindsetsAndTodos(feed, mindsets, todos);
	}

	private List<FeedsDto> createFeedDtos(List<Feed> feeds, List<UserDto> users) {
		List<FeedsDto> feedsDtos = feeds.stream().map(FeedsDto::of).collect(Collectors.toList());
		feedDtosSetUsersInfo(feedsDtos, users);
		return feedsDtos;
	}

	private void feedDtosSetUsersInfo(List<FeedsDto> feedsDtos, List<UserDto> userDtos) {
		feedsDtos.forEach(feedsDto -> {
			for (UserDto userDto : userDtos) {
				if (feedsDto.getUserId().equals(userDto.getId())) {
					feedsDto.registerUser(userDto);
					break;
				}
			}
		});
	}

	private static String userIdsToString(List<Feed> feeds) {
		return feeds.stream()
			.map(feed -> feed.getUserId().toString())
			.collect(Collectors.joining(","));
	}

	private List<UserDto> getUsersInfo(List<Feed> feeds) {
		//TODO user 정보 API를 통해 받아오기 (GET /users/profile?ids=1,2,3..)
		String userIds = userIdsToString(feeds);
		return List.of(new UserDto(1L, "닉네임1", "https://server/1.jpg"), new UserDto(2L, "닉네임2", "https://server/2.jpg"));
	}

	/*
	  내부테스트 및 샘플피드생성용 임시 메서드
	  1.0 출시버전에는 피드생성기능없이 출시
   */
	@Transactional
	public void createFeed(Feed feed, Mindset mindset, List<Todo> todos) {
		mindsetRepository.save(mindset);
		todoRepository.saveAll(todos);
	}
}
