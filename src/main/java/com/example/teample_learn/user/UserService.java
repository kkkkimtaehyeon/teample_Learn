package com.example.teample_learn.user;

import com.example.teample_learn.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void updateRoleCertificated(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저입니다."));
        user.updateRoleCertificated();
    }

    public SessionUser getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저입니다."));
        return SessionUser.builder().user(user).build();
    }
}
