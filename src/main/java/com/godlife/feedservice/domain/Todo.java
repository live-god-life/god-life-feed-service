package com.godlife.feedservice.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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
@DiscriminatorColumn(name = "type")
@Entity
@Table(name = "feed_todo")
public abstract class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long todoId;

	@Column(insertable = false, updatable = false)
	private String type;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private Integer depth;

	@Column(nullable = false)
	private Integer orderNumber;

	@Comment("투두 기간")
	private Integer period;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "feed_id")
	private Feed feed;

	public Todo(String title, Integer depth, Integer orderNumber, Feed feed) {
		this.title = title;
		this.depth = depth;
		this.orderNumber = orderNumber;
		this.feed = feed;
	}
}
