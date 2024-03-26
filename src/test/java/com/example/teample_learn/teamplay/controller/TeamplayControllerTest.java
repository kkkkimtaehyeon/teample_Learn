package com.example.teample_learn.teamplay.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.teample_learn.teamplay.domain.Posts;
import com.example.teample_learn.teamplay.dto.TeamplayResponseDto;
import com.example.teample_learn.teamplay.dto.TeamplaySaveRequestDto;
import com.example.teample_learn.teamplay.dto.TeamplayUpdateRequestDto;
import com.example.teample_learn.teamplay.repo.TeamplayRepository;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TeamplayControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TeamplayRepository teamplayRepository;

   static String author = "작성자";
   static String title = "제목";
   static String content = "내용";
   static String category = "개발";
   static Boolean contact = true;

    @After
    public void cleanUp() {
        teamplayRepository.deleteAll();
    }

    @Test
    public void posts_페이지() {

    }
    @Test
    public void Posts_조회한다() {

        TeamplaySaveRequestDto requestDto = TeamplaySaveRequestDto.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .contact(contact)
                .build();

        Long postId = teamplayRepository.save(requestDto.toEntity()).getId();

        String url = "http://localhost:" + port + "/post/" + postId;

        ResponseEntity<TeamplayResponseDto> responseEntity = restTemplate.getForEntity(url, TeamplayResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Posts posts = teamplayRepository.findById(postId).get();

        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getCategory()).isEqualTo(category);


    }

    @Test
    public void Posts_등록된다() {

        TeamplaySaveRequestDto requestDto = TeamplaySaveRequestDto.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .contact(contact)
                .build();

        String url = "http://localhost:" + port + "/post";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postList = teamplayRepository.findAll();

        Posts posts = postList.get(0);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getCategory()).isEqualTo(category);

    }

    @Test
    public void post_수정된다() {
        Posts savedPost = Posts.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .contact(contact)
                .build();

        Long postId = teamplayRepository.save(savedPost).getId();

        String expectedTitle = "title1";
        String expectedContent = "content1";
        String expectedCategory = "category1";
        Boolean expectedContact = false;

        TeamplayUpdateRequestDto requestDto = TeamplayUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .category(expectedCategory)
                .contact(expectedContact)
                .build();




        String url = "http://localhost:" + port + "/post/" + postId;

        //json 형식으로 http 요청을 전송
        HttpEntity<TeamplayUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postList = teamplayRepository.findAll();

        Posts posts = postList.get(0);
        assertThat(posts.getAuthor()).isEqualTo("작성자");
        assertThat(posts.getTitle()).isEqualTo(expectedTitle);
        assertThat(posts.getContent()).isEqualTo(expectedContent);
        assertThat(posts.getCategory()).isEqualTo(expectedCategory);
        assertThat(posts.getContact()).isEqualTo(expectedContact);
    }

    @Test
    public void posts_삭제된다() {
        Posts savedPost = Posts.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .contact(contact)
                .build();

        Long postId = teamplayRepository.save(savedPost).getId();
        String url = "http://localhost:" + port + "/post/" + postId;

        assertThat(teamplayRepository.count()).isEqualTo(1);

        restTemplate.delete(url);
        assertThat(teamplayRepository.count()).isEqualTo(0);
    }


}
