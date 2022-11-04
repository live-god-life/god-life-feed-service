package com.godlife.feedservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.godlife.feedservice.domain.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {
	List<Feed> findAllByCategory(Pageable page, String category);

	@Query(nativeQuery = true, value = "SELECT * FROM feed as f WHERE f.feed_id in :ids")
	List<Feed> findByIds(Pageable page, @Param("ids") List<Long> ids);
}
