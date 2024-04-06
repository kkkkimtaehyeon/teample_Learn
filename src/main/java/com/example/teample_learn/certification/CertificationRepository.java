package com.example.teample_learn.certification;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String> {
    Optional<CertificationEntity> findByCertificationNumberAndUserId(String certificationNumber, String id);
}
