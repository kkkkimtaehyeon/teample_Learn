package com.example.teample_learn.resume.dto;

import com.example.teample_learn.resume.domain.Resume;
import com.example.teample_learn.user.User;
import lombok.Data;

@Data
public class ResumeSaveRequestDto {

    User user;
    String title;
    String content;
    String phone;
    String email;
    String location;
    String work;
    String skills;

    public Resume toEntity() {
        return Resume.builder()
                .user(user)
                .title(title)
                .content(content)
                .phone(phone)
                .email(email)
                .location(location)
                .work(work)
                .skills(skills)
                .build();
    }
}
