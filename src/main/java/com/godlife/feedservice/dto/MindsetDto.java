package com.godlife.feedservice.dto;

import com.godlife.feedservice.domain.Mindset;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MindsetDto {
	private Long id;
	private String content;

	public static MindsetDto of(Mindset mindset) {
		return MindsetDto.builder()
			.id(mindset.getMindsetId())
			.content(mindset.getContent())
			.build();
	}
}
