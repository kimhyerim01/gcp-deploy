package com.example.crud.dto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonPropertyOrder({ "id", "content", "writer", "createdAt" })
public class CommentDto {
    private Long id;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
}
