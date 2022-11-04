package com.godlife.feedservice.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "feed_mindset")
public class Mindset {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mindsetId;

	@Comment("마인드셋 내용")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "feed_id")
	private Feed feed;

	private Mindset(String content, Feed feed) {
		this.content = content;
		this.feed = feed;
	}

	public static Mindset createMindset(String content, Feed feed) {
		return new Mindset(content, feed);
	}
}
