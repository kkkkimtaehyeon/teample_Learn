package com.example.teample_learn.post.controller;

import com.example.teample_learn.post.dto.PostRequestDto;
import com.example.teample_learn.post.dto.PostResponseDto;
import com.example.teample_learn.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{id}")
    public PostResponseDto view(@PathVariable("id") Long id) {
        return postService.findById(id);

    }


    @PostMapping("/post")
    public Long save(@RequestBody PostRequestDto requestDto) {
        return postService.save(requestDto);
    }
}
