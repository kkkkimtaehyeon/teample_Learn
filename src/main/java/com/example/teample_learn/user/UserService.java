package com.example.teample_learn.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void updateRoleCertificated(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 유저입니다."));
        user.updateRoleCertificated();
    }
}
