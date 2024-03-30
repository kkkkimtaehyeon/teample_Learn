package com.example.teample_learn.resume.dto;

import com.example.teample_learn.resume.domain.Resume;
import lombok.Data;

@Data
public class ResumeResponseDto {
    String title;
    String content;
    String realName;
    String phone;
    String email;
    String location;
    String work;
    String portfolios;
    String skills;

    public ResumeResponseDto(Resume resume) {
        this.title = resume.getTitle();
        this.content = resume.getContent();
        this.realName = resume.getRealName();
        this.phone = resume.getPhone();
        this.email = resume.getEmail();
        this.location = resume.getLocation();
        this.work = resume.getWork();
        this.portfolios = resume.getPortfolios();
        this.skills = resume.getSkills();
    }
}
