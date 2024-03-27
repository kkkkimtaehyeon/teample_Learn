package com.example.teample_learn.comment.service;

import com.example.teample_learn.comment.dto.CommentSaveRequestDto;
import com.example.teample_learn.comment.repo.CommentRepository;
import com.example.teample_learn.teamplay.domain.Posts;
import com.example.teample_learn.teamplay.repo.TeamplayRepository;
import com.example.teample_learn.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TeamplayRepository teamplayRepository;
    public Long save(Long id, CommentSaveRequestDto requestDto) {
        Posts posts = teamplayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다"));
        requestDto.setPosts(posts);

        return commentRepository.save(requestDto.toEntity()).getId();
    }
}
