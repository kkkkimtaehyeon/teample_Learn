package com.example.teample_learn.comment.service;

import com.example.teample_learn.comment.domain.Comment;
import com.example.teample_learn.comment.dto.CommentResponseDto;
import com.example.teample_learn.comment.dto.CommentSaveRequestDto;
import com.example.teample_learn.comment.dto.CommentUpdateRequestDto;
import com.example.teample_learn.comment.repo.CommentRepository;
import com.example.teample_learn.teamplay.domain.Posts;
import com.example.teample_learn.teamplay.repo.TeamplayRepository;
import com.example.teample_learn.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TeamplayRepository teamplayRepository;
    public Long save(Long id, CommentSaveRequestDto requestDto) {
        Posts posts = teamplayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다"));
        requestDto.setPosts(posts);

        Comment comment = requestDto.toEntity();

        return commentRepository.save(comment).getId();
    }

    public List<CommentResponseDto> getComments(Long id) {
        Posts posts = teamplayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다"));
        List<Comment> comments = commentRepository.findByPostsOrderByIdDesc(posts);
        List<CommentResponseDto> responseDtos = new ArrayList<>();

        for(Comment comment: comments) {
            responseDtos.add(new CommentResponseDto(comment));
        }

        return responseDtos;
    }

    public void delete(Long commentId) {
        // post마다 저장되는 댓글 id은 전체 순서? 아니면 포스트마다 인덱스가 1부터 시작?
        // Posts posts = teamplayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다"));

        commentRepository.delete(comment);
    }

    @Transactional
    public void update(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다"));
        comment.update(requestDto);
    }
}
