package com.example.teample_learn.post.controller;

import com.example.teample_learn.post.dto.PostResponseDto;
import com.example.teample_learn.post.dto.PostSaveRequestDto;
import com.example.teample_learn.post.dto.PostUpdateRequestDto;
import com.example.teample_learn.post.service.PostService;
import jakarta.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/teamplay")
@RestController
public class PostController {

    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping
    public Page<PostResponseDto> page(@PageableDefault(page = 0, size = 20, sort = "id", direction = Direction.DESC)
                                      Pageable pageable) {
        return postService.getPage(pageable);
    }
    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Long id) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("teamplay_view_form");
        mav.addObject("post", postService.findById(id));
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {

        return new ModelAndView("teamplay_create_form");
    }

    @PostMapping
    public Long save(PostSaveRequestDto requestDto) {
        /*SessionUser user = (SessionUser) httpSession.getAttribute("user");
        requestDto.setAuthor(user.getName());*/
        return postService.save(requestDto);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editForm(@PathVariable("id") Long id) {
        PostResponseDto responseDto = postService.findById(id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("post", responseDto);
        mav.addObject("id",id);
        mav.setViewName("teamplay_edit_form");

        return mav;
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable("id") Long id, PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        postService.delete(id);
    }

    @PostMapping("{id}/scrap")
    public Long addScrap(@PathVariable("id") Long id) {
        return postService.addScrap(id);
    }

    @DeleteMapping("/{id}/scrap")
    public Long subScrap(@PathVariable("id") Long id) {
        return postService.subScrap(id);
    }
}
