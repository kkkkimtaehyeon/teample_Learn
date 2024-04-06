package com.example.teample_learn.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "게스트"),
    USER("ROLE_USER", "회원"),
    CERTIFICATED("ROLE_CERTIFICATED", "인증된 회원");

    private final String key;
    private final String title;
}
