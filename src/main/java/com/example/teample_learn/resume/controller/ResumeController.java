package com.example.teample_learn.resume.controller;

import com.example.teample_learn.config.auth.dto.SessionUser;
import com.example.teample_learn.resume.dto.ResumeSaveRequestDto;
import com.example.teample_learn.resume.service.ResumeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("/resume")
    public Long saveResume(ResumeSaveRequestDto requestDto, HttpSession session) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        return resumeService.save(requestDto, user);
    }
}
