package com.godlife.feedservice.repository;

import com.godlife.feedservice.domain.Feed;
import com.godlife.feedservice.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByFeedAndDepth(Feed feed, int depth);
}
