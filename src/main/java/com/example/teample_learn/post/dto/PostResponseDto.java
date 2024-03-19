package com.example.teample_learn.post.dto;

import com.example.teample_learn.post.Posts;
import lombok.Builder;
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

    public PostResponseDto(Posts post) {
        this.id = post.getId();
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.view = post.getView();
        this.scrap = post.getScrap();
    }
}
