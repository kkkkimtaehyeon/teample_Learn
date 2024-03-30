package com.example.teample_learn.comment.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.teample_learn.comment.domain.Comment;
import com.example.teample_learn.comment.dto.CommentSaveRequestDto;
import com.example.teample_learn.comment.repo.CommentRepository;
import com.example.teample_learn.teamplay.dto.TeamplaySaveRequestDto;
import com.example.teample_learn.teamplay.repo.TeamplayRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Mock
    TeamplayRepository teamplayRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    static String author = "작성자";
    static String title = "제목";
    static String content = "내용";
    static String category = "개발";
    static Boolean meeting = true;
    static String deadline = "23-03-27";
    static Integer quota = 1;
    static String duration = "1개월";
    static String TEST_COMMENT = "test comment";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void 댓글_저장() {
        TeamplaySaveRequestDto requestDto = TeamplaySaveRequestDto.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .meeting(meeting)
                .deadline(deadline)
                .quota(quota)
                .duration(duration)
                .build();

        //when(teamplayRepository.save(any(Teamplay.class))).thenReturn(1L);
        Long postId = teamplayRepository.save(requestDto.toEntity()).getId();


        CommentSaveRequestDto commentSaveRequestDto = CommentSaveRequestDto.builder()
                .content(TEST_COMMENT)
                .build();

        Long commentId = commentService.save(postId, commentSaveRequestDto);

        // 댓글이 적절하게 저장되었는지 확인
        verify(commentRepository, times(1)).save(any(Comment.class));

        Comment savedComment = commentRepository.findById(commentId).orElse(null);
        assertEquals(TEST_COMMENT, savedComment.getContent());
        assertEquals(postId, savedComment.getPosts().getId());
    }
}
