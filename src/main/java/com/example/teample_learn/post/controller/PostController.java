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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView view(@PathVariable("id") Long id) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("post_view_form");
        mav.addObject("post", postService.findById(id));
        return mav;
    }

    /*@GetMapping("/post/{id}")
    public PostResponseDto view(@PathVariable("id") Long id) {

        return postService.findById(id);
    }

    @GetMapping("/post/view")
    public ModelAndView getViewHtml() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("post_view_form");
        return mav;
    }*/



    @GetMapping("/post/create")
    public ModelAndView createForm() {
        return new ModelAndView("post_create_form");
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
