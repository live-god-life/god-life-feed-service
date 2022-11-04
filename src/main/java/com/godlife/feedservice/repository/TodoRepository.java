package com.godlife.feedservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.godlife.feedservice.domain.Feed;
import com.godlife.feedservice.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findByFeedAndDepth(Feed feed, int depth);
}
