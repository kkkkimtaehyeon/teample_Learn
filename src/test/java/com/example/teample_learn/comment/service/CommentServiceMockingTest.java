package com.example.teample_learn.comment.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.teample_learn.comment.domain.Comment;
import com.example.teample_learn.comment.dto.CommentSaveRequestDto;
import com.example.teample_learn.comment.repo.CommentRepository;
import com.example.teample_learn.teamplay.domain.Posts;
import com.example.teample_learn.teamplay.repo.TeamplayRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentServiceMockingTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private TeamplayRepository teamplayRepository;

    @InjectMocks
    private CommentService commentService;

    @Spy
    Comment spyComment;

    @Test
    @DisplayName("댓글 저장 테스트")
    public void saveComment() {
        Posts posts = new Posts();//빈 엔티티, 테스트에 영향을 주지 않음.
        CommentSaveRequestDto requestDto = new CommentSaveRequestDto("test", posts);
        Comment savedComment = requestDto.toEntity();

        Mockito.when(teamplayRepository.findById(1L)).thenReturn(Optional.of(posts));
        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(requestDto.toEntity());
        Mockito.when(spyComment.getId()).thenReturn(1L);

        Long savedId = commentService.save(1L, requestDto);

        assertThat(savedComment.getId()).isEqualTo(savedId);
    }
}
