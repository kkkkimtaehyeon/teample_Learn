package com.example.teample_learn.auth;

import com.example.teample_learn.certification.dto.EmailCertificationCheckRequestDto;
import com.example.teample_learn.certification.dto.EmailCertificationRequestDto;
import com.example.teample_learn.certification.dto.EmailCertificationResponseDto;
import javax.swing.text.StyledEditorKit.BoldAction;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    //ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> validCertificationNumber(EmailCertificationCheckRequestDto requestDto);
}
