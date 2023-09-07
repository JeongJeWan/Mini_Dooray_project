package com.nhnacademy.task_api.entity;

import com.nhnacademy.task_api.dto.comment.CreateCommentDto;
import com.nhnacademy.task_api.dto.comment.ModifyCommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comments")
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "register_id", nullable = false)
    private String registerId;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public Comment(Task task, CreateCommentDto commentDto) {
        this.task = task;
        this.registerId = commentDto.getRegisterId();
        this.content = commentDto.getContent();
        this.createdAt = LocalDateTime.now();
    }

    public void modifyComment(ModifyCommentDto modifyComment) {
        this.content = modifyComment.getContent();
        this.modifiedAt = LocalDateTime.now();
    }
}
