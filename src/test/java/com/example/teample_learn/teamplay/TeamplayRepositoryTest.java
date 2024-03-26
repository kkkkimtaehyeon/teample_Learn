package com.example.teample_learn.teamplay;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.teample_learn.teamplay.domain.Posts;
import com.example.teample_learn.teamplay.repo.TeamplayRepository;
import java.util.List;
import org.aspectj.lang.annotation.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamplayRepositoryTest {

    @Autowired
    TeamplayRepository teamplayRepository;

    @After("sdf")
    public void cleanUp() {
        teamplayRepository.deleteAll();
    }

    @Test
    public void 게시물저장_불러오기() {
        String author = "작성자";
        String title = "제목";
        String content = "내용";
        String category = "개발";

        teamplayRepository.save(
                Posts.builder()
                        .author(author)
                        .content(content)
                        .title(title)
                        .category(category)
                .build()
        );

        List<Posts> postList = teamplayRepository.findAll();

        Posts posts = postList.get(0);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getCategory()).isEqualTo(category);

    }


}
