package com.godlife.feedservice.domain.enums;

public enum Category {
	CAREER("커리어"), HOBBY("취미"), EXERCISE("운동"), GAME("게임");

	private final String categoryName;

	Category(String categoryName) {
		this.categoryName = categoryName;
	}
}
