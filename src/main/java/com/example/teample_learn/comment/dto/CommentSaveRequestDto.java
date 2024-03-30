package com.example.teample_learn.comment.dto;

import com.example.teample_learn.comment.domain.Comment;
import com.example.teample_learn.teamplay.domain.Posts;
import com.example.teample_learn.user.User;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentSaveRequestDto {

    String content;
    Posts posts;

    @Builder
    public CommentSaveRequestDto (String content, Posts posts) {
        this.content = content;
        this.posts = posts;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .posts(posts)
                .build();
    }
}
