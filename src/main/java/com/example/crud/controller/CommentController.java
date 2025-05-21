package com.example.crud.controller;

import com.example.crud.dto.CommentDto;
import com.example.crud.entity.Comment;
import com.example.crud.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentDto addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        Comment saved = commentService.addComment(postId, comment);
        return CommentDto.builder()
                .id(saved.getId())
                .content(saved.getContent())
                .writer(saved.getWriter())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @GetMapping
    public List<CommentDto> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId).stream()
                .map(comment -> CommentDto.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .writer(comment.getWriter())
                        .createdAt(comment.getCreatedAt())
                        .build())
                .toList();
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
