package com.example.teample_learn.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.aspectj.lang.annotation.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest  {

    @Autowired
    PostRepository postRepository;

    @After("sdf")
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시물저장_불러오기() {
        String author = "작성자";
        String title = "제목";
        String content = "내용";
        String category = "개발";

        postRepository.save(
                Posts.builder()
                        .author(author)
                        .content(content)
                        .title(title)
                        .category(category)
                .build()
        );

        List<Posts> postList = postRepository.findAll();

        Posts posts = postList.get(0);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getCategory()).isEqualTo(category);

    }


}
