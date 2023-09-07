package com.nhnacademy.task_api.service;

import com.nhnacademy.task_api.dto.comment.CreateCommentDto;
import com.nhnacademy.task_api.dto.comment.ModifyCommentDto;

public interface CommentService {

    void createComment(CreateCommentDto createComment);

    void modifyComment(ModifyCommentDto modifyComment);

    void deleteComment(Long id);
}
