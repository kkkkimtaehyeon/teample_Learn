package com.example.teample_learn.teamplay.repo;


import com.example.teample_learn.teamplay.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Boolean existsByUserEmailAndTeamplayId(String userEmail, Long teamplayId);
    Likes findByUserEmailAndTeamplayId(String userEmail, Long teamplayId);
}
