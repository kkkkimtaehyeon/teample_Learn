package com.example.teample_learn.config.auth.dto;

import com.example.teample_learn.user.User;
import lombok.Getter;

@Getter
public class SessionUser {

    private Long id;
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
