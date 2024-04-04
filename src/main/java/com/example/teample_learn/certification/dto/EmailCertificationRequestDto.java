package com.example.teample_learn.certification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmailCertificationRequestDto {

    @NotBlank
    private String id;

    @Email
    @NotBlank
    private String email;
}
