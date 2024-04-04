package com.example.teample_learn.provider;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailProvider {
    private final JavaMailSender javaMailSender;
    private static final String SUBJECT = "[팀플런 학교 인증 메일입니다.]";

    public boolean sendCertificationMail(String email, String certificationNumber) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email);
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);

        }catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;

    }

    private String getCertificationMessage(String certificationNumber) {
        String certificationMessage = "";
        certificationMessage += "<h1 style = 'text-align:center;>[팀플런 학교 인증 메일입니다.]</h1>";
        certificationMessage += "<h3 style = 'text-align:ceneter;'>인증코드 : <strong style='font-size: 32px; letter-spacing:8px;'>" + certificationNumber + "</strong></h3>";
        return certificationMessage;
    }

}
