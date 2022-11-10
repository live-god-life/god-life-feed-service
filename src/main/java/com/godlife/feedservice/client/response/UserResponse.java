package com.godlife.feedservice.client.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class UserResponse {
	private String status;
	private List<UserDto> data;
	private Integer code;
	private String message;

	@Data
	@Getter
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
