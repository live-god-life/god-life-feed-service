package com.godlife.feedservice.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("TASK")
@Entity
public class TodoTask extends Todo{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_todo_id")
    private Todo todo;

    protected TodoTask(String title, Integer depth, Integer orderNumber, Todo parentTodo, Feed feed) {
        super(title, depth, orderNumber, feed);
        this.todo = parentTodo;
    }

    public static TodoTask createTodoTask(String title, int depth, int orderNumber, int period, Todo parentTodo, Feed feed) {
        return new TodoTask(title, depth, orderNumber, parentTodo, feed);
    }
}
