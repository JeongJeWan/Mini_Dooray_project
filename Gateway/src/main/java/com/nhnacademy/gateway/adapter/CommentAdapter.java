package com.nhnacademy.gateway.adapter;

import static com.nhnacademy.gateway.adapter.AdapterTemplate.*;
import com.nhnacademy.gateway.dto.comment.CreateCommentRequest;
import com.nhnacademy.gateway.dto.comment.ModifyCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CommentAdapter {

    private static final String COMMENT = "/task/comments";

    private final RestTemplate restTemplate;

    public void createComment(CreateCommentRequest createRequest) {

        create(restTemplate, COMMENT, createRequest);
    }

    public void modifyComment(ModifyCommentRequest modifyRequest) {

        modify(restTemplate, COMMENT, modifyRequest);
    }

    public void deleteComment(Long id) {

        delete(restTemplate, COMMENT, id);
    }
}
