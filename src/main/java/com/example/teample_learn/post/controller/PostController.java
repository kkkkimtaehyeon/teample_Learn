package com.example.teample_learn.post.controller;

import com.example.teample_learn.post.dto.PostResponseDto;
import com.example.teample_learn.post.dto.PostSaveRequestDto;
import com.example.teample_learn.post.dto.PostUpdateRequestDto;
import com.example.teample_learn.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public Page<PostResponseDto> page(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC)
                                      Pageable pageable) {
        return postService.getPage(pageable);
    }
    @GetMapping("/post/{id}")
    public PostResponseDto view(@PathVariable("id") Long id) {
        return postService.findById(id);
    }

    @PostMapping("/post")
    public Long save(PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/post/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);

    }

    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable("id") Long id) {
        postService.delete(id);
    }

    @PostMapping("/post/{id}/scrap")
    public Long addScrap(@PathVariable("id") Long id) {
        return postService.addScrap(id);
    }

    @DeleteMapping("/post/{id}/scrap")
    public Long subScrap(@PathVariable("id") Long id) {
        return postService.subScrap(id);
    }
}
