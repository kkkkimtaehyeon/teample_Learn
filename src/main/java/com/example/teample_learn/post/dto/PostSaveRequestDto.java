package com.example.teample_learn.post.dto;

import com.example.teample_learn.post.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostSaveRequestDto {

    String author;
    String title;
    String content;
    String category;
    String requiredSkills;
    String tags;
    Boolean contact;
    String className;
    int classDivision;

    @Builder
    public PostSaveRequestDto(String author, String title, String content, String category, String requiredSkills, String tags, Boolean contact, String className, int classDivision) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.category = category;
        this.requiredSkills = requiredSkills;
        this.tags = tags;
        this.contact = contact;
        this.className = className;
        this.classDivision = classDivision;
    }

    public Posts toEntity() {
        return Posts.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .requiredSkills(requiredSkills)
                .tags(tags)
                .contact(contact)
                .className(className)
                .classDivision(classDivision)
                .build();
    }
}
