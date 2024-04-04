package com.example.teample_learn.auth;

import com.example.teample_learn.certification.CertificationEntity;
import com.example.teample_learn.certification.CertificationRepository;
import com.example.teample_learn.certification.dto.EmailCertificationRequestDto;
import com.example.teample_learn.certification.dto.EmailCertificationResponseDto;
import com.example.teample_learn.certification.provider.EmailProvider2;
import com.example.teample_learn.common.CertificationNumber;
import com.example.teample_learn.common.ResponseDto;
import com.example.teample_learn.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailProvider2 emailProvider2;
    private final CertificationRepository certificationRepository;
    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            String userId = dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsById(Long.parseLong(userId));
            if(isExistId) return EmailCertificationResponseDto.duplicateId();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSucceed = emailProvider2.sendCertificationMail(email, certificationNumber);
            if(!isSucceed) return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);
            certificationRepository.save(certificationEntity);


        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }
}
