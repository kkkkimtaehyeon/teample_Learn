package com.example.teample_learn.resume.service;

import com.example.teample_learn.config.auth.dto.SessionUser;
import com.example.teample_learn.resume.domain.Resume;
import com.example.teample_learn.resume.dto.ResumeResponseDto;
import com.example.teample_learn.resume.dto.ResumeSaveRequestDto;
import com.example.teample_learn.resume.dto.ResumeUpdateRequestDto;
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

        return resumeRepository.save(requestDto.toEntity()).getId();
    }
    public ResumeResponseDto getResume(SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 유저입니다."));
        Resume resume = resumeRepository.findByUser(user);
        if(resume == null) {
            return null;
        }

        return resume.toDto();
    }

    @Transactional
    public void update(ResumeUpdateRequestDto requestDto, SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 유저입니다."));
        Resume resume = resumeRepository.findByUser(user);//resume 없으면 null 반환
        resume.update(requestDto);
    }
}
