package com.godlife.feedservice.dto;

import com.godlife.feedservice.domain.Todo;
import com.godlife.feedservice.domain.TodoFolder;
import com.godlife.feedservice.domain.TodoTask;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TodoDto {
    public static TodoDto of(Todo todo) {
        if (todo instanceof TodoTask) {
            TodoTask todoTask = (TodoTask) todo;
            return TodoDto.TodoTaskDto.builder()
                    .id(todoTask.getId())
                    .title(todoTask.getTitle())
                    .type("TASK")
                    .depth(todoTask.getDepth())
                    .orderNumber(todoTask.getOrderNumber())
                    .build();
        } else {
            TodoFolder todoFolder = (TodoFolder) todo;
            return TodoDto.TodoFolderDto.builder()
                    .id(todoFolder.getId())
                    .title(todoFolder.getTitle())
                    .type("FOLDER")
                    .depth(todoFolder.getDepth())
                    .orderNumber(todoFolder.getOrderNumber())
                    .childTodos(todoFolder.getTodos().stream().map(TodoDto::of).collect(Collectors.toList()))
                    .build();
        }
    }

    @Getter
    @Builder
    static class TodoTaskDto extends TodoDto{
        private Long id;
        private String title;
        private String type;
        private int depth;
        private int orderNumber;
    }

    @Getter
    @Builder
    static class TodoFolderDto extends TodoDto{
        private Long id;
        private String title;
        private String type;
        private int depth;
        private int orderNumber;
        private List<TodoDto> childTodos;
    }
}
