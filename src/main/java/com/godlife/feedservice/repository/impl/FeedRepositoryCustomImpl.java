package com.godlife.feedservice.repository.impl;

import static com.godlife.feedservice.domain.QFeed.*;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Pageable;

import com.godlife.feedservice.dto.FeedsDto;
import com.godlife.feedservice.dto.QFeedsDto;
import com.godlife.feedservice.repository.FeedRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class FeedRepositoryCustomImpl implements FeedRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	public FeedRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<FeedsDto> findAllByCategoryAndFeedIds(Pageable page, String category, List<Long> feedIds) {
		return queryFactory
			.select(
				new QFeedsDto(
					feed.feedId,
					feed.category,
					feed.viewCount,
					feed.viewCount,
					feed.todoCount,
					feed.todoScheduleCount,
					feed.image,
					feed.userId))
			.from(feed)
			.where(isFeedIdIn(feedIds),
				isEqCategory(category))
			.fetch();
	}

	private static BooleanExpression isEqCategory(String category) {
		if (Objects.isNull(category)) {
			return null;
		}
		return feed.category.eq(category);
	}

	private BooleanExpression isFeedIdIn(List<Long> feedIds) {
		if (Objects.isNull(feedIds)) {
			return null;
		}
		return feed.feedId.in(feedIds);
	}
}
