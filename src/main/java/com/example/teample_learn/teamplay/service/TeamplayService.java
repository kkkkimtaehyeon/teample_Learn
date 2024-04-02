package com.example.teample_learn.teamplay.service;

import com.example.teample_learn.teamplay.domain.Posts;
import com.example.teample_learn.teamplay.dto.TeamplayResponseDto;
import com.example.teample_learn.teamplay.dto.TeamplaySaveRequestDto;
import com.example.teample_learn.teamplay.dto.TeamplayUpdateRequestDto;
import com.example.teample_learn.teamplay.repo.TeamplayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamplayService {

    private final TeamplayRepository teamplayRepository;

    public Long save(TeamplaySaveRequestDto requestDto) {
        return teamplayRepository.save(requestDto.toEntity()).getId();
    }

    public Page<TeamplayResponseDto> getPages(Pageable pageable) {
        Page<Posts> postsPage = teamplayRepository.findAll(pageable);

        return postsPage.map(TeamplayResponseDto::new);
    }

    public Page<TeamplayResponseDto> getPages(String category, Pageable pageable) {
        Page<Posts> postsPage = teamplayRepository.findAllByCategory(category, pageable);

        return postsPage.map(TeamplayResponseDto::new);
    }

    public Page<TeamplayResponseDto> getSearchPages(String keyword, Pageable pageable) {
        Page<Posts> postsPage = teamplayRepository.findAllByTitleContaining(keyword, pageable);

        return postsPage.map(TeamplayResponseDto::new);
    }

    public TeamplayResponseDto findById(Long id) {
        Posts post = teamplayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));

        return new TeamplayResponseDto(post);
    }

    @Transactional
    public Long update(Long id, TeamplayUpdateRequestDto requestDto) {
        Posts post = teamplayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));
        post.update(requestDto);

        return id;
    }

    public void delete(Long id) {
        Posts post = teamplayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));
        teamplayRepository.delete(post);
    }

    public Long addLike(Long id) {
        Posts post = teamplayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));
        post.addLikeCount();

        return id;
    }

    public Long subLike(Long id) {
        Posts post = teamplayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다" + id));
        post.subLikeCount();
        return id;
    }

}
