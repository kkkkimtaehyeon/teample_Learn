package com.example.teample_learn.comment.repo;

import com.example.teample_learn.comment.domain.Comment;
import com.example.teample_learn.teamplay.domain.Posts;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findAllByPosts(Posts posts);
    List<Comment> findByPostsOrderByIdDesc(Posts posts);
}
