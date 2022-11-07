package com.godlife.feedservice.domain;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Comment;

import com.godlife.feedservice.domain.converter.StringListConverter;
import com.godlife.feedservice.domain.enums.RepetitionType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("TASK")
@Entity
public class TodoTask extends Todo {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_todo_id")
	private Todo todo;
	@Enumerated(EnumType.STRING)
	@Comment("기간 type")
	private RepetitionType repetitionType;

	@Convert(converter = StringListConverter.class)
	@Comment("기간 파라미터")
	private List<String> repetitionParams;

	@Comment("알림")
	private String notification;

	private TodoTask(String title, Integer depth, Integer orderNumber, RepetitionType repetitionType, List<String> repetitionParams, String notification, Feed feed) {

		super(title, depth, orderNumber, feed);
		this.repetitionType = repetitionType;
		this.repetitionParams = repetitionParams;
		this.notification = notification;
	}

	public static Todo createTodoTask(String title, Integer depth, Integer orderNumber, RepetitionType repetitionType, List<String> repetitionParams, String notification, Feed feed) {
		return new TodoTask(title, depth, orderNumber, repetitionType, repetitionParams, notification, feed);
	}
}
