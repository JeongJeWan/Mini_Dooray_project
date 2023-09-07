package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.comment.CreateCommentRequest;
import com.nhnacademy.gateway.dto.comment.ModifyCommentRequest;
import com.nhnacademy.gateway.service.task.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects/{projectId}/tasks/{taskId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public String create(@PathVariable Long projectId, @PathVariable Long taskId,
                               @ModelAttribute @Valid CreateCommentRequest createRequest,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

        }

        commentService.createComment(createRequest);

        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }

    @PostMapping("/modify")
    public String modify(@PathVariable Long projectId, @PathVariable Long taskId,
                               @ModelAttribute @Valid ModifyCommentRequest modifyRequest,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

        }

        commentService.modifyComment(modifyRequest);

        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long projectId, @PathVariable Long taskId,
                               @PathVariable Long id) {
        commentService.delete(id);

        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }
}
