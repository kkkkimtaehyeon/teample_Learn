package com.example.teample_learn.teamplay.controller;

import com.example.teample_learn.config.auth.dto.SessionUser;
import com.example.teample_learn.teamplay.dto.TeamplayResponseDto;
import com.example.teample_learn.teamplay.dto.TeamplaySaveRequestDto;
import com.example.teample_learn.teamplay.dto.TeamplayUpdateRequestDto;
import com.example.teample_learn.teamplay.service.TeamplayService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/teamplay")
@RestController
public class TeamplayController {

    private final TeamplayService teamplayService;
    private final HttpSession httpSession;

    @GetMapping
    public Page<TeamplayResponseDto> allPages(
            @RequestParam(value = "keyword", required = false) String keyword,
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Direction.DESC) Pageable pageable) {

        if(keyword != null) {
            return teamplayService.getSearchPages(keyword, pageable);
        }
        return teamplayService.getPages(pageable);
    }

    /*@GetMapping("/public")
    public Page<TeamplayResponseDto> publicPages(@PageableDefault(page = 0, size = 20, sort = "id", direction = Direction.DESC) Pageable pageable) {

        return teamplayService.getPages("public", pageable);
    }
    @GetMapping("/private")
    public Page<TeamplayResponseDto> privatePages(@PageableDefault(page = 0, size = 20, sort = "id", direction = Direction.DESC) Pageable pageable) {

        return teamplayService.getPages("private", pageable);
    }*/

    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Long id, HttpSession session) {

        SessionUser user = (SessionUser) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teamplay_view_form");
        mav.addObject("id", id);
        mav.addObject("post", teamplayService.findById(id));
        if(user != null) {
            mav.addObject("user", user.getName());
        }

        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {

        return new ModelAndView("teamplay_create_form");
    }

    @PostMapping
    public Long save(TeamplaySaveRequestDto requestDto) {
        /*SessionUser user = (SessionUser) httpSession.getAttribute("user");
        requestDto.setAuthor(user.getName());*/
        return teamplayService.save(requestDto);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editForm(@PathVariable("id") Long id) {
        TeamplayResponseDto responseDto = teamplayService.findById(id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("post", responseDto);
        mav.addObject("id",id);
        mav.setViewName("teamplay_edit_form");

        return mav;
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable("id") Long id, TeamplayUpdateRequestDto requestDto) {
        return teamplayService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        teamplayService.delete(id);
    }


}
