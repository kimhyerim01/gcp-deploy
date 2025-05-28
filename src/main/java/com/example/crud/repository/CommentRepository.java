package com.example.crud.repository;

import com.example.crud.entity.Comment;
import com.example.crud.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post); // 게시글 별 댓글 조회
}
