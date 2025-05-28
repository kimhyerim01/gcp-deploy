package com.example.crud.service;

import com.example.crud.dto.CommentDto;
import com.example.crud.dto.PostWithCommentDto;
import com.example.crud.entity.Post;
import com.example.crud.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 전체 게시글 + 댓글 DTO 반환
    public List<PostWithCommentDto> findAll() {
        return postRepository.findAllWithComments().stream()
                .map(post -> PostWithCommentDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .writer(post.getWriter())
                        .reported(post.isReported())
                        .createdAt(post.getCreatedAt())
                        .comments(post.getComments().stream()
                                .map(comment -> CommentDto.builder()
                                        .id(comment.getId())
                                        .content(comment.getContent())
                                        .writer(comment.getWriter())
                                        .createdAt(comment.getCreatedAt())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }

    // 개별 게시글 조회
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    // 게시글 생성
    public Post createPost(Post post) {
        post.setCreatedAt(java.time.LocalDateTime.now());
        return postRepository.save(post);
    }

    // 게시글 수정
    public Post updatePost(Long id, Post updatedPost) {
        Post existing = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));
        existing.setTitle(updatedPost.getTitle());
        existing.setContent(updatedPost.getContent());
        existing.setWriter(updatedPost.getWriter());
        return postRepository.save(existing);
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // 게시글 신고 처리
    public void reportPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));
        post.setReported(true);
        postRepository.save(post);
    }
}
