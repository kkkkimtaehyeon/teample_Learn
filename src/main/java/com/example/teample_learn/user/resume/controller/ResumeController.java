package com.example.teample_learn.user.resume.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class ResumeController {
    @GetMapping("/resume/create")
    public ModelAndView createResume() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("resume_create_form");
        return mav;
    }
}
