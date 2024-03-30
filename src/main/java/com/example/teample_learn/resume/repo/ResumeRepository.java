package com.example.teample_learn.resume.repo;

import com.example.teample_learn.resume.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
