package com.example.teample_learn.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostUpdateRequestDto {

    String title;
    String content;
    String category;
    String requiredSkills;
    String tags;
    Boolean contact;
    String className;
    int classDivision;

    @Builder
    public PostUpdateRequestDto (String title, String content, String category, String requiredSkills, String tags, Boolean contact, String className, int classDivision) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.requiredSkills = requiredSkills;
        this.tags = tags;
        this.contact = contact;
        this.className = className;
        this.classDivision = classDivision;
    }
}
