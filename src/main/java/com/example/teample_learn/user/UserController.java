package com.example.teample_learn.user;

import com.example.teample_learn.config.auth.dto.SessionUser;
import com.example.teample_learn.teamplay.dto.TeamplayResponseDto;
import com.example.teample_learn.teamplay.service.TeamplayService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final TeamplayService teamplayService;
    private final UserService userService;
    @GetMapping("/user/profile")
    public String userProfile(HttpSession session, Model model) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", user);

        return "profile";
    }


    @GetMapping("/user/{id}/activity")
    public String userActivityForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user" ,userService.getUser(id));
        return "activity";
    }
    @ResponseBody
    @GetMapping("/api/user/{id}/activity")
    public Page<TeamplayResponseDto> userActivity(@PathVariable("id") Long id, Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.DESC) Pageable pageable) {
        return teamplayService.findAllById(id, pageable);
    }
}
