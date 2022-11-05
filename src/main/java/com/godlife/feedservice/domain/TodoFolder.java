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
}
