package com.godlife.feedservice.client.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BookmarkResponse {
	private String status;
	private List<BookmarkDto> data;
	private Integer code;
	private String message;

	@Getter
	public static class BookmarkDto {
		private Long feedId;
		private boolean bookmarkStatus;
	}
}
