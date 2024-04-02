package com.example.teample_learn.teamplay.controller;

import com.example.teample_learn.config.auth.dto.SessionUser;
import com.example.teample_learn.teamplay.service.LikesService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikesService likesService;

    @PostMapping("teamplay/{id}/like")
    public int like(@PathVariable("id") Long id, HttpSession httpSession) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return likesService.likes(user.getEmail(), id);
    }
}
