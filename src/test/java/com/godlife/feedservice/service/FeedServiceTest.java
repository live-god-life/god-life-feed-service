package com.godlife.feedservice.service;

import com.godlife.feedservice.domain.Feed;
import com.godlife.feedservice.domain.Mindset;
import com.godlife.feedservice.domain.TodoFolder;
import com.godlife.feedservice.domain.TodoTask;
import com.godlife.feedservice.dto.FeedDetailDto;
import com.godlife.feedservice.dto.FeedsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class FeedServiceTest {
    @Autowired FeedService feedService;

    @Test
    @DisplayName("피드의 상세내용을 조회한다.")
    void getFeedDetail() {
        //given
        saveSampleFeed();

        //when
        List<FeedsDto> feeds = feedService.getFeeds(PageRequest.of(0, 25), "ALL", null);

        Long feedId = 1L;

        for (FeedsDto feed : feeds) {
            feedId = feed.getFeedId();
        }
        
        
        FeedDetailDto feed = feedService.getFeedById(feedId);

        //then
        assertThat(feed).isNotNull();
        assertThat(feed.getMindsets().size()).isEqualTo(1);
        assertThat(feed.getTodos().size()).isEqualTo(2);
    }
    @Test
    @DisplayName("피드리스트를 조회한다.")
    void getFeeds() {
        //given
        saveSampleFeed();

        //when
        List<FeedsDto> feeds = feedService.getFeeds(PageRequest.of(0, 25), "ALL", null);

        //then
        assertThat(feeds.size()).isGreaterThanOrEqualTo(1);
    }

    private void saveSampleFeed() {
        Feed feed = Feed.createFeed(
                "중소기업 식품연구원의 네카라쿠배 개발자 입성기",
                "CAREER",
                "안녕하세요, 라인 넥스트 5년차 엔지니어 조성빈이라고 합니다.\n" +
                        "\n" +
                        "경희대 조리서비스경영학과를 전공하였고 복수전공으로 식품생명공학과를 전공하였는대요. 식품 연구원으로 커리어를 시작했습니다.\n" +
                        "\n" +
                        "대학에서는 좋은 논문도 많이 보고 이상적인 이론도 많이 공부하지만 실제로 하는 일은 공장에 생산을 가려면 한 3시간 정도 차를 타고 가서 허름한 모텔에서 잠을 청하고 그 다음날 아침에 일찍 일어나서 생산을 시작하게 되거든요.\n" +
                        "\n" +
                        "그런 상황에서 월급은 210-220만원 정도에 개선될 수 있는 사이클이 아닌 것 같다는 느낌을 느꼈을 때 매우 절망적이였던 것 같아요. 2년정도 하다보니까 이 업계를 후회없이 한번 떠나볼 수 있지 않을까 라는 생각이 들었습니다.\n" +
                        "\n" +
                        "다른 옵션이 많이 있는 IT 쪽으로 피봇팅을 준비하자는 선택을 했던것 같습니다\n" +
                        "3-4개월 동안 하루에 12-14시간 공부하더라도 실무자의 강의를 들으면서 개발자의 해킹스러운 문화를 습득하고 모르는게 있더라도 기본은 다 비슷하게 시간을 투자해 유튜브를 찾아보고 온라인 강의를 들어서라도 배우면 금방 익힐 수 있어라는 관점들이 큰 경험이였던 것 같습니다.\n" +
                        "\n" +
                        "100군데 정도 이력서를 돌리고 면접기획가 오면 매우 좋은 공부의 기회라고 생각하는 관점으로 접근했는데요.\n" +
                        "헬로네이쳐라는 곳에서 데이터 엔지니어로 근무해보지 않겠냐는 제안을 주셨고 IT 쪽에서 커리어를 시작할 수 있었습니다.\n" +
                        "\n" +
                        "제가 커리어를 전환하며 주변에 걱정과 우려도 많았지만 사는건 레벨업이 아닌 스펙트럼을 넓히는 거란 얘길 들었습니다. 어떤 말보다 용기가 되었습니다.\n" +
                        "\n" +
                        " 기존에 제가 바라봤던 커리어는 대학교 전공과도 관련이 매우 깊어야 되고 매우 한정적이고 수종적인 자세로 진행해야 했다는 그런 관점을 가졌던 반면에 능동적으로 직무 전환을 하고 나서는 내가 실력을 쌓고 시장을 찾고 기회를 포착해서 노동력을 팔 수 있다면 다른 산업에 갈 수도 있고 다른 직무로 전환할 수도 있구나, IT 쪽에서 커리어를 시작만 한다면 회사를 이직해서 조금 더 좋은 곳으로 가는건 직무 전환을 경험한 나에겐 별로 어려운 일이 아니겠구나라는 생각이 들었습니다.\n" +
                        "\n" +
                        "제가 데이터 엔지니어로 직무 전환을 하기 위해서 실천했던 리스트를 공유드립니다.\n" +
                        "이글을 읽는 여러분께서도 많은 도움이 되었으면 좋겠습니다.\n" +
                        "\n",
                "http://server/feeds/images/1.jpg",
                1L);

        Mindset mindset = Mindset.createMindset("사는건 레벨업이 아닌 스펙트럼을 넓히는 거란 얘길 들었다. 어떤 말보다 용기가 된다.", feed);

        TodoTask todoTask1 = TodoTask.createTodoTask(
                "포트폴리오 완성",
                1,
                1,
                90,
                null,
                feed);

        TodoFolder todoFolder2 = TodoFolder.createTodoFolder(
                "개발프로젝트 해보기",
                1,
                2,
                feed
        );

        TodoTask todoTask2_1 = TodoTask.createTodoTask(
                "파이썬공부",
                2,
                1,
                90,
                todoFolder2,
                feed);

        TodoTask todoTask2_2 = TodoTask.createTodoTask(
                "SQL공부",
                2,
                2,
                90,
                todoFolder2,
                feed);

        todoFolder2.addChildTodos(List.of(todoTask2_1,todoTask2_2));
        feedService.createFeed(feed, mindset, List.of(todoTask1, todoFolder2));
    }
}