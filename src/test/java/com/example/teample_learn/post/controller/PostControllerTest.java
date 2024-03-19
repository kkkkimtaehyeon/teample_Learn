package com.example.teample_learn.post.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.teample_learn.post.PostRepository;
import com.example.teample_learn.post.Posts;
import com.example.teample_learn.post.dto.PostRequestDto;
import com.example.teample_learn.post.dto.PostResponseDto;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;


    @After
    public void cleanUp() {
        postRepository.deleteAll();
    }
    @Test
    public void Posts_조회한다() {
        String author = "작성자";
        String title = "제목";
        String content = "내용";
        String category = "개발";

        PostRequestDto requestDto = PostRequestDto.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .build();

        Long postId = postRepository.save(requestDto.toEntity()).getId();

        String url = "http://localhost:" + port + "/post/" + postId;

        ResponseEntity<PostResponseDto> responseEntity = restTemplate.getForEntity(url, PostResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Posts posts = postRepository.findById(postId).get();

        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getCategory()).isEqualTo(category);


    }

    @Test
    public void Posts_등록된다() {
        String author = "작성자";
        String title = "제목";
        String content = "내용";
        String category = "개발";

        PostRequestDto requestDto = PostRequestDto.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .build();

        String url = "http://localhost:" + port + "/post";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postList = postRepository.findAll();

        Posts posts = postList.get(0);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getCategory()).isEqualTo(category);

    }
}
