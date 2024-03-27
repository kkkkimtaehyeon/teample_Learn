package com.example.teample_learn.comment.dto;

import com.example.teample_learn.comment.domain.Comment;
import com.example.teample_learn.teamplay.domain.Posts;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentResponseDto {

    String content;
    //User user

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        //this.user = comment.getUser();
    }
}
