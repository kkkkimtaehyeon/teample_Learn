package com.example.teample_learn.user;

import com.example.teample_learn.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    @GetMapping("/profile")
    public String userInfo(HttpSession session, Model model) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", user);

        return "profile";
    }
}
