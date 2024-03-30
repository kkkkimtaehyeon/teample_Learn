package com.example.teample_learn.comment.controller;

import com.example.teample_learn.comment.dto.CommentResponseDto;
import com.example.teample_learn.comment.dto.CommentSaveRequestDto;
import com.example.teample_learn.comment.dto.CommentUpdateRequestDto;
import com.example.teample_learn.comment.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/teamplay/{id}/comment")
    public List<CommentResponseDto> getComments(@PathVariable("id") Long id) {
        List<CommentResponseDto> responseDtos = commentService.getComments(id);
        return responseDtos;
    }

    @PostMapping("/teamplay/{id}/comment")
    public Long saveComment(@PathVariable("id") Long id, @RequestBody CommentSaveRequestDto requestDto) {//유저 정보 추가 필요
        return commentService.save(id, requestDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);
    }

    @PutMapping("/comment/{commentId}")
    public void udpateComment(@PathVariable("commentId") Long commentId, CommentUpdateRequestDto requestDto) {//json은 안되는데 formData만 됨
        commentService.update(commentId, requestDto);
    }


}
