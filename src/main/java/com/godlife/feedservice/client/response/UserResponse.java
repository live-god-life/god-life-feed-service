package com.godlife.feedservice.client.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponse {
	private String status;
	private List<UserDto> data;
	private Integer code;
	private String message;

	@AllArgsConstructor
	@Data
	public static class UserDto {
		private Long userId;
		private String nickname;
		@JsonIgnore
		private String type;
		@JsonIgnore
		private String identifier;
		@JsonIgnore
		private String refreshToken;
		@JsonIgnore
		private String email;
		private String image;
	}
}
