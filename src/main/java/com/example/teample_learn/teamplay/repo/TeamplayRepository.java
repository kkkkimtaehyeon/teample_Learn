package com.example.teample_learn.teamplay.repo;

import com.example.teample_learn.teamplay.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamplayRepository extends JpaRepository<Posts, Long> {

    //Page<Posts> findAllByOrderByIdDesc(Pageable pageable);
    Page<Posts> findAllByMajor(String major, Pageable pageable);
    Page<Posts> findAllByTitleContaining(String keyword, Pageable pageable);

    Page<Posts> findAllById(Long userId, Pageable pageable);
}
