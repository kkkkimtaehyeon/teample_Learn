package com.example.teample_learn.teamplay.service;

import com.example.teample_learn.teamplay.domain.Likes;
import com.example.teample_learn.teamplay.domain.Posts;
import com.example.teample_learn.teamplay.repo.LikesRepository;
import com.example.teample_learn.teamplay.repo.TeamplayRepository;
import com.example.teample_learn.user.User;
import com.example.teample_learn.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final TeamplayRepository teamplayRepository;

    public int likes(String userEmail, Long teamplayId) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Posts posts = teamplayRepository.findById(teamplayId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 팀플레이입니다"));
        Boolean isLikes = likesRepository.existsByUserEmailAndTeamplayId(userEmail, teamplayId);

        if(isLikes) {
            return deleteLikes(userEmail, teamplayId);
        }
        return saveLikes(posts, userEmail, teamplayId);
    }

    public int saveLikes(Posts posts, String userEmail, Long teamplayId) {

        likesRepository.save(Likes.builder()
                .userEmail(userEmail)
                .teamplayId(teamplayId)
                .build()).getId();

        posts.addLikeCount();

        return posts.getLikeCount();
    }

    public int deleteLikes(String userEmail, Long teamplayId) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        Posts posts = teamplayRepository.findById(teamplayId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 팀플레이입니다"));
        Likes likes = likesRepository.findByUserEmailAndTeamplayId(userEmail, teamplayId);
        likesRepository.delete(likes);
        posts.subLikeCount();
        return posts.getLikeCount();
    }
}
