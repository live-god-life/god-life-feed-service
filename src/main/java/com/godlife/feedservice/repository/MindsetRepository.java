package com.godlife.feedservice.repository;

import com.godlife.feedservice.domain.Feed;
import com.godlife.feedservice.domain.Mindset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MindsetRepository extends JpaRepository<Mindset, Long> {
    List<Mindset> findByFeed(Feed feed);
}
