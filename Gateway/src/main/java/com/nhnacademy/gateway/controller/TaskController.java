package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.task.CreateTaskRequest;
import com.nhnacademy.gateway.dto.task.ModifyTaskRequest;
import com.nhnacademy.gateway.dto.task.TaskDto;
import com.nhnacademy.gateway.service.task.MilestoneService;
import com.nhnacademy.gateway.service.task.TagService;
import com.nhnacademy.gateway.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/projects/{projectId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final MilestoneService milestoneService;
    private final TagService tagService;
    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/create")
    public String create(@PathVariable Long projectId,
                               @ModelAttribute("task") CreateTaskRequest createRequest,
                               HttpSession httpSession, Model model) {

        String username = (String) redisTemplate.opsForHash().get(httpSession.getId(), "username");

        model.addAttribute("username", username);
        model.addAttribute("milestones", milestoneService.findMilestonesByProjectId(projectId));
        model.addAttribute("tagList", tagService.findTagsByProjectId(projectId));
        model.addAttribute("projectId", projectId);
        model.addAttribute("url", "create");

        return "task/task-form";
    }

    @PostMapping("/create")
    public String doCreate(@PathVariable Long projectId,
                                 @ModelAttribute("task") @Valid CreateTaskRequest createRequest,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("url", "create");
            return "task/task-form";
        }

        taskService.createTask(createRequest);

        return "redirect:/projects/" + projectId;
    }


    @GetMapping("/{id}")
    public String findTask(@PathVariable Long projectId, @PathVariable Long id,
                                 HttpSession httpSession, Model model) {

        TaskDto task = taskService.findTask(id);

        String username = (String) redisTemplate.opsForHash().get(httpSession.getId(), "username");

        model.addAttribute("username", username);
        model.addAttribute("projectId", projectId);
        model.addAttribute("task", task);

        return "task/task";
    }

    @GetMapping("/{id}/modify")
    public String modify(@PathVariable Long projectId, @PathVariable Long id,
                               HttpSession httpSession, Model model) {


        model.addAttribute("task", taskService.findTask(id));
        model.addAttribute("url", "modify");
        model.addAttribute("projectId", projectId);
        model.addAttribute("milestones", milestoneService.findMilestonesByProjectId(projectId));
        model.addAttribute("tagList", tagService.findTagsByProjectId(projectId));


        return "task/task-form";
    }

    @PostMapping("/{id}/modify")
    public String doModify(@PathVariable Long projectId, @PathVariable Long id,
                                 @ModelAttribute("task") @Valid ModifyTaskRequest modifyRequest,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("url", "modify");
            return "task/task-form";
        }

        taskService.modifyTask(modifyRequest);

        return "redirect:/projects/" + projectId + "/tasks/" + id;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long projectId, @PathVariable Long id) {

        taskService.deleteTask(id);

        return "redirect:/projects/" + projectId;
    }
}
