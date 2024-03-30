package com.example.teample_learn.resume.service;

import com.example.teample_learn.config.auth.dto.SessionUser;
import com.example.teample_learn.resume.domain.Resume;
import com.example.teample_learn.resume.dto.ResumeResponseDto;
import com.example.teample_learn.resume.dto.ResumeSaveRequestDto;
import com.example.teample_learn.resume.repo.ResumeRepository;
import com.example.teample_learn.user.User;
import com.example.teample_learn.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(ResumeSaveRequestDto requestDto, SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 유저입니다."));
        requestDto.setUser(user);

        Resume savedResume =  resumeRepository.save(requestDto.toEntity());
        user.updateResume(savedResume);

        return savedResume.getId();
    }
    public ResumeResponseDto getResume(SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 유저입니다."));
        Resume resume = user.getResume();
        if(resume == null) {
            return null;
        }

        return resume.toDto();
    }
}
