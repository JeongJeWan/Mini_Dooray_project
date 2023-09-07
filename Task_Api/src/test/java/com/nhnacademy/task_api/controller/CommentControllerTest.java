package com.nhnacademy.task_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task_api.dto.comment.CreateCommentDto;
import com.nhnacademy.task_api.dto.comment.ModifyCommentDto;
import com.nhnacademy.task_api.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private CommentService commentService;
    @Test
    @DisplayName("댓글 생성")
    void testCreateComment() throws Exception{
        CreateCommentDto createCommentDto = new CreateCommentDto();
        ReflectionTestUtils.setField(createCommentDto,"taskId",1L);
        ReflectionTestUtils.setField(createCommentDto,"registerId","marco");
        ReflectionTestUtils.setField(createCommentDto,"content","testcontent");

        String createCommentDtoJson = mapper.writeValueAsString(createCommentDto);

        mockMvc.perform(post("/task/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCommentDtoJson))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("댓글 수정")
    void testModifyComment() throws Exception{
        ModifyCommentDto modifyCommentDto = new ModifyCommentDto();
        ReflectionTestUtils.setField(modifyCommentDto,"id",1L);
        ReflectionTestUtils.setField(modifyCommentDto,"content","testcontent");

        String modifyCommentDtoJson = mapper.writeValueAsString(modifyCommentDto);

        mockMvc.perform(put("/task/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyCommentDtoJson))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("댓글 삭제")
    void testDeleteComment() throws Exception{
        Long commentId = 1L;

        mockMvc.perform(delete("/task/comments/{id}",commentId))
                .andExpect(status().isNoContent());
    }
}