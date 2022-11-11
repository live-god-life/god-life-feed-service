package com.godlife.feedservice.api;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godlife.feedservice.api.request.CreateFeedRequest;
import com.godlife.feedservice.api.response.ApiResponse;
import com.godlife.feedservice.api.response.ResponseCode;
import com.godlife.feedservice.exception.NoSuchFeedException;
import com.godlife.feedservice.service.FeedService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class FeedController {
	private final FeedService feedService;
	private static final String USER_ID_HEADER = "x-user";
	private static final int DEFAULT_PAGE = 25;

	@GetMapping("/feeds")
	public ResponseEntity<ApiResponse> getFeeds(
		@PageableDefault(size = DEFAULT_PAGE) Pageable page,
		@RequestHeader(USER_ID_HEADER) Long userId,
		@RequestParam(value = "category", required = false) String category,
		@RequestParam(value = "ids", required = false) List<Long> feedIds) {

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(feedService.getFeeds(page, userId, category, feedIds)));
	}

	@GetMapping("/feeds/{feedId}")
	public ResponseEntity<ApiResponse> getFeedDetail(
		@RequestHeader(USER_ID_HEADER) Long userId,
		@PathVariable(value = "feedId") Long feedId) {

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(feedService.getFeedDetail(userId, feedId)));
	}

	/*
		  내부테스트 및 샘플피드생성용 임시 메서드
		  1.0 출시버전에는 피드생성기능없이 출시
	*/
	@PostMapping("/feeds")
	public ResponseEntity<ApiResponse> createFeed(
		@RequestBody CreateFeedRequest request) {
		feedService.createFeed(request);
		return null;
	}

	@ExceptionHandler
	public ResponseEntity<ApiResponse> noSuchFeedException(NoSuchFeedException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(ResponseCode.ERROR, e.getMessage()));
	}
}
