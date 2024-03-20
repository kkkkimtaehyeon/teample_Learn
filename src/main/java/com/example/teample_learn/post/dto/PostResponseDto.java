package com.example.teample_learn.post.dto;

import com.example.teample_learn.post.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostResponseDto {

    Long id;
    String author;
    String title;
    String content;
    String category;
    int view;
    int scrap;
    String requiredSkills;
    String tags;
    Boolean contact;
    String className;
    String classDivision;

    public PostResponseDto(Posts post) {
        this.id = post.getId();
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.view = post.getViewCount();
        this.scrap = post.getScrapCount();
        this.requiredSkills = post.getRequiredSkills();
        this.tags = post.getTags();
        this.contact = post.getContact();
        this.className = post.getClassName();
        this.classDivision = post.getClassDivision();
    }
}
