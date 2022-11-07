package com.godlife.feedservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
	private Long userId;
	private String nickname;
	private String image;
}
