package com.godlife.feedservice.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Feed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;
    @Comment("피드 제목")
    private String title;
    @Comment("피드 내용")
    @Lob
    private String content;
    @Comment("피드 카테고리")
    private String category;

    //===사용자정보===
    @Comment("작성자 ID")
    private Long userId;

    //===카운팅===
    @Comment("뷰 카운트")
    private int viewCount;
    @Comment("가져가기 카운트")
    private int pickCount;

    //===이미지===
    @Comment("이미지 Path")
    private String image;

    private Feed(String title, String category, String content, String image, Long userId) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.viewCount = 0;
        this.pickCount = 0;
        this.image = image;
        this.userId = userId;
    }

    public static Feed createFeed(String title, String category, String content, String image, Long userId) {
        return new Feed(title, category, content, image, userId);
    }

    public void plusViewCount() {
        this.viewCount++;
    }
}
