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

    @Builder
    public PostSaveRequestDto(String author, String title, String content, String category) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Posts toEntity() {
        return Posts.builder()
                .author(author)
                .title(title)
                .content(content)
                .category(category)
                .build();
    }
}
