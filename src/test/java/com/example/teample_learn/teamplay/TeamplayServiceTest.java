package com.example.teample_learn.teamplay;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.teample_learn.teamplay.domain.Posts;
import com.example.teample_learn.teamplay.dto.TeamplaySaveRequestDto;
import com.example.teample_learn.teamplay.repo.TeamplayRepository;
import com.example.teample_learn.teamplay.service.TeamplayService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class TeamplayServiceTest {

    @Autowired
    TeamplayRepository teamplayRepository;

    @Autowired
    TeamplayService teamplayService;

    static String AUTHOR = "작성자";
    static String TITLE = "제목";
    static String CONTENT = "내용";
    static String MAJOR = "컴공";
    static Boolean MEETING = true;
    static String DEADLINE = "23-03-27";
    static Integer QUOTA = 1;
    static String DURATION = "1개월";
    static String CLASS_NAME = "알고리즘";
    static Integer CLASS_DIVISION = 1;

    @AfterEach
    public void clear() {
        teamplayRepository.deleteAll();
    }

    @DisplayName("팀플레이 생성 테스트")
    @Test
    public void 팀플레이_생성() {
        //given
        TeamplaySaveRequestDto requestDto = TeamplaySaveRequestDto.builder()
                .author(AUTHOR)
                .content(CONTENT)
                .title(TITLE)
                .major(MAJOR)
                .meeting(MEETING)
                .deadline(DEADLINE)
                .duration(DURATION)
                .quota(QUOTA)
                .className(CLASS_NAME)
                .classDivision(CLASS_DIVISION)
                .build();

        //when
        Long savedId = teamplayService.save(requestDto);

        //then
        Posts post = teamplayRepository.findById(savedId).get();
        assertThat(post.getId()).isEqualTo(savedId);
    }

    @Test
    @DisplayName("팀플레이 삭제")
    public void delete() {
        //given
        TeamplaySaveRequestDto requestDto = TeamplaySaveRequestDto.builder()
                .author(AUTHOR)
                .content(CONTENT)
                .title(TITLE)
                .major(MAJOR)
                .meeting(MEETING)
                .deadline(DEADLINE)
                .duration(DURATION)
                .quota(QUOTA)
                .className(CLASS_NAME)
                .classDivision(CLASS_DIVISION)
                .build();
        Long savedId = teamplayService.save(requestDto);

        //when
        Posts savedPost = teamplayRepository.findById(savedId).get();
        teamplayRepository.delete(savedPost);

        //then
        assertThat(teamplayRepository.count()).isEqualTo(0);

    }
}
