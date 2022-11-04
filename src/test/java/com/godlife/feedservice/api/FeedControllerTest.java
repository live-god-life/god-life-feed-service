package com.godlife.feedservice.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
//@AutoConfigureRestDocs
@Sql(scripts = "classpath:sql/init.sql")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest
class FeedControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("모든 피드내용을 조회한다_카테고리 전체일경우")
	void getFeedAll() throws Exception {
		mockMvc.perform(get("/feeds")
				.queryParam("page", "0")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("모든 피드내용을 조회한다_카테고리 커리어 CAREER")
	void getFeedsByCategory() throws Exception {
		mockMvc.perform(get("/feeds")
				.queryParam("page", "1")
				.queryParam("category", "CAREER")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("모든 피드내용을 조회한다_파라미터 아이디배열에따라")
	void getFeedByIds() throws Exception {
		mockMvc.perform(get("/feeds")
				.queryParam("ids", "1,2,3")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("피드 상세내용을 조회한다.")
	void getFeedDetail() throws Exception {
		mockMvc.perform(get("/feeds/{feedId}", 1)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}
}