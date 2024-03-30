package com.example.teample_learn.comment.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentUpdateRequestDto {
    String content;

    //빌더 필요?
    @Builder
    public CommentUpdateRequestDto(String content) {
        this.content = content;
    }
}
