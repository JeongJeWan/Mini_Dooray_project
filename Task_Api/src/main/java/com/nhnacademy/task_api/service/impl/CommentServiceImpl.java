package com.nhnacademy.task_api.service.impl;

import com.nhnacademy.task_api.dto.comment.CreateCommentDto;
import com.nhnacademy.task_api.dto.comment.ModifyCommentDto;
import com.nhnacademy.task_api.entity.Comment;
import com.nhnacademy.task_api.entity.Task;
import com.nhnacademy.task_api.exception.CommentNotFoundException;
import com.nhnacademy.task_api.exception.TaskNotFoundException;
import com.nhnacademy.task_api.repository.comment.CommentRepository;
import com.nhnacademy.task_api.repository.task.TaskRepository;
import com.nhnacademy.task_api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;


    @Override
    public void createComment(CreateCommentDto createComment) {
        Task task = taskRepository.findById(createComment.getTaskId()).orElseThrow(TaskNotFoundException::new);

        commentRepository.save(new Comment(task, createComment));
    }

    @Override
    public void modifyComment(ModifyCommentDto modifyComment) {

        Comment comment = commentRepository.findById(modifyComment.getId()).orElseThrow(CommentNotFoundException::new);

        comment.modifyComment(modifyComment);
    }

    @Override
    public void deleteComment(Long id) {

        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);

        commentRepository.delete(comment);
    }
}
