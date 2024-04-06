package com.example.teample_learn.main.controller;

import com.example.teample_learn.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        return "index";
    }
}
