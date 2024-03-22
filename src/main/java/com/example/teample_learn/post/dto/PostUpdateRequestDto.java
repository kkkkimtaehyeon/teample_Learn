package com.example.teample_learn.post.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class PostUpdateRequestDto {

    String author;
    String title;
    String content;
    String tags;
    Boolean meeting;
    String category;
    String className;
    int classDivision;
    String deadline;
    String duration;
    int quota;
    String skills;
    String contact;

    @Builder
    public PostUpdateRequestDto(String title, String content, String tags, Boolean meeting, String category, String className,
                              int classDivision, String deadline, String duration, int quota, String skills, String contact) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.meeting = meeting;
        this.category = category;
        this.className = className;
        this.classDivision = classDivision;
        this.deadline = deadline;
        this.duration = duration;
        this.quota = quota;
        this.skills = skills;
        this.contact = contact;
    }
}
