package com.godlife.feedservice.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("FOLDER")
@Entity
public class TodoFolder extends Todo {
	@Embedded
	private Todos childTodos;

	private TodoFolder(String title, Integer depth, Integer orderNumber, Todos childTodos, Feed feed) {
		super(title, depth, orderNumber, feed);
		this.childTodos = childTodos;
	}

	public static TodoFolder createTodoFolder(String title, Integer depth, Integer orderNumber, List<Todo> childTodos, Feed feed) {
		Todos todos = new Todos(childTodos);
		return new TodoFolder(title, depth, orderNumber, todos, feed);
	}
}
