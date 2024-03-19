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

    @Builder
    public PostUpdateRequestDto (String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
