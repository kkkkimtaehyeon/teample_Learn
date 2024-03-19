package com.example.teample_learn.post.service;

import com.example.teample_learn.post.PostRepository;
import com.example.teample_learn.post.Posts;
import com.example.teample_learn.post.dto.PostRequestDto;
import com.example.teample_learn.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Long save(PostRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    public PostResponseDto findById(Long id) {
        Posts post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));

        return new PostResponseDto(post);
    }

}
