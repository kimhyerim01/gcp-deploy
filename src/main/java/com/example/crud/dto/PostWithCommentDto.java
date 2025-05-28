package com.example.crud.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"id", "title", "content", "writer", "reported", "createdAt", "comment"})
public class PostWithCommentDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private boolean reported;
    private LocalDateTime createdAt;
    private List<CommentDto> comments;
}
