package com.godlife.feedservice.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Comment;

import com.godlife.feedservice.domain.enums.Category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Feed {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feedId;
	@Enumerated(EnumType.STRING)
	@Comment("피드 카테고리")
	private Category category;
	@Comment("피드 제목")
	private String title;
	@Comment("피드 내용")
	@Lob
	private String content;

	//===사용자정보===
	@Comment("작성자 ID")
	private Long userId;

	//===카운팅===
	@Comment("뷰 카운트")
	private int viewCount;
	@Comment("가져가기 카운트")
	private int pickCount;
	@Comment("투두 카운트")
	private int todoCount;
	@Comment("투두 일정")
	private int todoScheduleDay;

	//===이미지===
	@Comment("이미지 Path")
	private String image;
}
