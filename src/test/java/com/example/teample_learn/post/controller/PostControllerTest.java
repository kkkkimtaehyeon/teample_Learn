package com.example.teample_learn.post.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.teample_learn.post.repo.PostRepository;
import com.example.teample_learn.post.domain.Posts;
import com.example.teample_learn.post.dto.PostSaveRequestDto;
import com.example.teample_learn.post.dto.PostResponseDto;
import com.example.teample_learn.post.dto.PostUpdateRequestDto;
import java.util.List;
import org.assertj.core.api.Assertions;
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

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
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

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
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

    @Test
    public void post_수정된다() {
        Posts savedPost = Posts.builder()
                .author("author")
                .title("title")
                .content("content")
                .category("category")
                .build();

        Long postId = postRepository.save(savedPost).getId();

        String expectedTitle = "title1";
        String expectedContent = "content1";
        String expectedCategory = "category1";

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .category(expectedCategory)
                .build();



        String url = "http://localhost:" + port + "/post/" + postId;

        //json 형식으로 http 요청을 전송
        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postList = postRepository.findAll();

        Posts posts = postList.get(0);
        assertThat(posts.getAuthor()).isEqualTo("author");
        assertThat(posts.getTitle()).isEqualTo(expectedTitle);
        assertThat(posts.getContent()).isEqualTo(expectedContent);
        assertThat(posts.getCategory()).isEqualTo(expectedCategory);
    }

    @Test
    public void posts_삭제된다() {
        Posts savedPost = Posts.builder()
                .author("author")
                .title("title")
                .content("content")
                .category("category")
                .build();

        Long postId = postRepository.save(savedPost).getId();
        String url = "http://localhost:" + port + "/post/" + postId;

        assertThat(postRepository.count()).isEqualTo(1);

        restTemplate.delete(url);
        assertThat(postRepository.count()).isEqualTo(0);
    }

}
