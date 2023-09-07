package com.nhnacademy.task_api.controller;

import com.nhnacademy.task_api.dto.comment.CreateCommentDto;
import com.nhnacademy.task_api.dto.comment.ModifyCommentDto;
import com.nhnacademy.task_api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CreateCommentDto createComment) {

        commentService.createComment(createComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyComment(@RequestBody ModifyCommentDto modifyComment) {

        commentService.modifyComment(modifyComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {

        commentService.deleteComment(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
