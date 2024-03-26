package com.example.teample_learn.comment.repo;

import com.example.teample_learn.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long> {
}
