package com.example.teample_learn.post.repo;

import com.example.teample_learn.post.domain.Posts;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    Page<Posts> findAllByOrderByIdDesc(Pageable pageable);
}
