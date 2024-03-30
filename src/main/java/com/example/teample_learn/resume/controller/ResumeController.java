package com.example.teample_learn.resume.controller;

import com.example.teample_learn.config.auth.dto.SessionUser;
import com.example.teample_learn.resume.dto.ResumeResponseDto;
import com.example.teample_learn.resume.dto.ResumeSaveRequestDto;
import com.example.teample_learn.resume.dto.ResumeUpdateRequestDto;
import com.example.teample_learn.resume.service.ResumeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/resume/view")
    public ModelAndView viewResume() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("resume_view_form");

        return mav;
    }

    @GetMapping("/resume")
    public ResumeResponseDto get(HttpSession httpSession) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        return resumeService.getResume(user);
    }

    @GetMapping("/resume/create")
    public ModelAndView resumeCreateForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("resume_create_form");

        return mav;
    }

    @PostMapping("/resume")
    public Long saveResume(ResumeSaveRequestDto requestDto, HttpSession session) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        return resumeService.save(requestDto, user);
    }

    @GetMapping("/resume/edit")
    public ModelAndView resumeUpdateForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("resume_edit_form");
        return mav;
    }

    @PutMapping("/resume")
    public void updateResume(ResumeUpdateRequestDto requestDto, HttpSession session) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        resumeService.update(requestDto, user);
    }


}
