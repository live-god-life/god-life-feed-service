package com.godlife.feedservice.client.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookmarkResponse {
	private String status;
	private List<BookmarkDto> data;
	private Integer code;
	private String message;

	@AllArgsConstructor
	@Getter
	public static class BookmarkDto {
		private Long feedId;
		private boolean bookmarkStatus;
	}
}
