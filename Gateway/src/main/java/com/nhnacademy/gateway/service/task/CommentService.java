package com.nhnacademy.gateway.service.task;

import com.nhnacademy.gateway.adapter.CommentAdapter;
import com.nhnacademy.gateway.dto.comment.CreateCommentRequest;
import com.nhnacademy.gateway.dto.comment.ModifyCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentAdapter commentAdapter;

    public void createComment(CreateCommentRequest createCommentRequest) {
        commentAdapter.createComment(createCommentRequest);
    }

    public void modifyComment(ModifyCommentRequest modifyCommentRequest) {
        commentAdapter.modifyComment(modifyCommentRequest);
    }

    public void delete(Long id) {
        commentAdapter.deleteComment(id);
    }
}
