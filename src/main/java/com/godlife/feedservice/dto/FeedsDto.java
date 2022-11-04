package com.godlife.feedservice.dto;

import com.godlife.feedservice.domain.Feed;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeedsDto {
	private Long feedId;
	private String title;

	//===카운팅===
	private int viewCount;
	private int pickCount;

	//===이미지===
	private String image;

	//===사용자정보===
	private Long userId;
	private UserDto user;
	private boolean bookMarkStatus;

	public static FeedsDto of(Feed feed) {
		return FeedsDto.builder()
			.feedId(feed.getFeedId())
			.title(feed.getTitle())
			.viewCount(feed.getViewCount())
			.pickCount(feed.getPickCount())
			.image(feed.getImage())
			.userId(feed.getUserId())
			.build();
	}

	public void registerUser(UserDto userDto) {
		this.user = userDto;
	}
}
