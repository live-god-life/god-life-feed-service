package com.godlife.feedservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("FOLDER")
@Entity
public class TodoFolder extends Todo {
	@OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<TodoTask> todos = new ArrayList<>();

	protected TodoFolder(String title, Integer depth, Integer orderNumber, Feed feed) {
		super(title, depth, orderNumber, feed);
	}

	public static TodoFolder createTodoFolder(String title, int depth, int orderNumber, Feed feed) {
		return new TodoFolder(title, depth, orderNumber, feed);
	}

	public void addChildTodos(List<TodoTask> todos) {
		this.todos.addAll(todos);
	}
}
