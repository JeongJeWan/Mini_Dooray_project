package com.nhnacademy.task_api.service;

import com.nhnacademy.task_api.dto.comment.CreateCommentDto;
import com.nhnacademy.task_api.dto.comment.ModifyCommentDto;
import com.nhnacademy.task_api.entity.Comment;
import com.nhnacademy.task_api.entity.Task;
import com.nhnacademy.task_api.repository.comment.CommentRepository;
import com.nhnacademy.task_api.repository.task.TaskRepository;
import com.nhnacademy.task_api.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private TaskRepository taskRepository;

    @Test
    @DisplayName("댓글 생성")
    void createComment() {
        CreateCommentDto createComment = spy(new CreateCommentDto());

        when(createComment.getTaskId()).thenReturn(1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(mock(Task.class)));
        when(commentRepository.save(any(Comment.class))).thenReturn(mock(Comment.class));

        commentService.createComment(createComment);

        verify(taskRepository, times(1)).findById(anyLong());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    @DisplayName("댓글 수정")
    void modifyComment() {
        ModifyCommentDto modifyComment = new ModifyCommentDto();
        Comment spyComment = spy(new Comment());

        ReflectionTestUtils.setField(modifyComment, "id", 1L);
        ReflectionTestUtils.setField(modifyComment, "content", "modifyComment");

        when(commentRepository.findById(modifyComment.getId())).thenReturn(Optional.of(spyComment));
        doNothing().when(spyComment).modifyComment(modifyComment);

        commentService.modifyComment(modifyComment);

        verify(commentRepository, times(1)).findById(modifyComment.getId());
        verify(spyComment, times(1)).modifyComment(modifyComment);
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() {
        Long commentId = 1L;

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(mock(Comment.class)));

        doNothing().when(commentRepository).delete(any(Comment.class));

        commentService.deleteComment(commentId);

        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(1)).delete(any(Comment.class));
    }
}