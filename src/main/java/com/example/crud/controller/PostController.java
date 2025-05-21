package com.example.crud.controller;

import com.example.crud.dto.PostWithCommentDto;
import com.example.crud.entity.Post;
import com.example.crud.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 전체 게시글 조회 (댓글 포함 DTO로 반환)
    @GetMapping
    public List<PostWithCommentDto> getAllPosts() {
        return postService.findAll();
    }

    // 개별 게시글 조회 (엔티티 그대로 반환)
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));
    }

    // 게시글 생성
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    // 게시글 신고
    @PostMapping("/{id}/report")
    public void reportPost(@PathVariable Long id) {
        postService.reportPost(id);
    }
}