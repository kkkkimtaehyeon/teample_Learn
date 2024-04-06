package com.example.teample_learn.auth;

import com.example.teample_learn.certification.dto.EmailCertificationCheckRequestDto;
import com.example.teample_learn.certification.dto.EmailCertificationRequestDto;
import com.example.teample_learn.certification.dto.EmailCertificationResponseDto;
import com.example.teample_learn.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            EmailCertificationRequestDto requestBody) {

        return authService.emailCertification(requestBody);
    }

    @PostMapping("/email-certification/check")
    public ResponseEntity<? super EmailCertificationResponseDto> checkEmailCertification(@RequestBody EmailCertificationCheckRequestDto requestDto) {

        return authService.validCertificationNumber(requestDto);
    }
}
