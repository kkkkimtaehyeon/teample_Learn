package com.example.teample_learn.comment.controller;

import com.example.teample_learn.comment.dto.CommentResponseDto;
import com.example.teample_learn.comment.dto.CommentSaveRequestDto;
import com.example.teample_learn.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    /*@GetMapping("/teamplay/{id}/comment")
    public CommentResponseDto viewComment(@RequestParam("id") Long id) {
        return commentService.getComment(id);
    }*/

    @PostMapping("/teamplay/{id}/comment")
    public Long  createComment(@RequestParam("id") Long id, CommentSaveRequestDto requestDto) {//유저 정보 추가 필요
        return commentService.save(id, requestDto);
    }
}
