package com.example.teample_learn.user;

import com.example.teample_learn.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/profile")
    public String userProfile(HttpSession session, Model model) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/{id}/activity")
    public String userActivity(@PathVariable("id") Long id, Model model) {



        return "activity";
    }
}
