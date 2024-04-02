package com.example.teample_learn.teamplay.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "teamplay_id", nullable = false)
    private Long teamplayId;

    @Builder
    public Likes(String userEmail, Long teamplayId) {
        this.userEmail = userEmail;
        this.teamplayId = teamplayId;
    }
}
