package com.example.teample_learn.post.service;

import com.example.teample_learn.post.repo.PostRepository;
import com.example.teample_learn.post.domain.Posts;
import com.example.teample_learn.post.dto.PostSaveRequestDto;
import com.example.teample_learn.post.dto.PostResponseDto;
import com.example.teample_learn.post.dto.PostUpdateRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    public Page<PostResponseDto> getPage(int page) {
        int pageSize = 10;
        Page<Posts> postsPage = postRepository.findAll(PageRequest.of(page, pageSize, Sort.by(Direction.DESC, "id")));

        return postsPage.map(PostResponseDto::new);

    }
    public PostResponseDto findById(Long id) {
        Posts post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));

        return new PostResponseDto(post);
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Posts post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));
        post.update(requestDto);

        return id;
    }

    public void delete(Long id) {
        Posts post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));
        postRepository.delete(post);
    }

    public Long addScrap(Long id) {
        Posts post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));
        post.addScrapCount();

        return id;
    }

    public Long subScrap(Long id) {
        Posts post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));
        post.subScrapCount();
        return id;
    }

}
