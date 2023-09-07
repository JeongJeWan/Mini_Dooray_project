package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.project.AddProjectMemeberRequest;
import com.nhnacademy.gateway.dto.project.CreateProjectRequest;
import com.nhnacademy.gateway.dto.project.ProjectDto;
import com.nhnacademy.gateway.dto.task.TaskListResponse;
import com.nhnacademy.gateway.dto.user.UserDto;
import com.nhnacademy.gateway.service.task.ProjectService;
import com.nhnacademy.gateway.service.task.TaskService;
import com.nhnacademy.gateway.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final RedisTemplate<String, String> redisTemplate;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/list")
    public String userProject(HttpSession httpSession, Model model) {


        String username = (String) redisTemplate.opsForHash().get(httpSession.getId(), "username");
        log.info("session username = {}", username);

        List<ProjectDto> projects = projectService.findProjectByUserId(username);
        model.addAttribute("projects", projects);

        return "project/list";
    }

    @GetMapping("/{id}")
    public String project(@PathVariable Long id, Model model) {


        ProjectDto project = projectService.findProject(id);
        List<TaskListResponse> tasks = taskService.findTasksByProjectId(id);

        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);

        return "project/project";
    }

    @GetMapping("/{id}/members")
    public String addMembers(Model model) {


        List<UserDto> users = userService.findUsers();

        model.addAttribute("users", users);

        return "project/add-member-form";
    }

    @GetMapping("/create")
    public String projectForm(CreateProjectRequest createRequest) {
        return "project/project-form";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute @Valid CreateProjectRequest createRequest,
                                      BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "project/project-form";
        }

        String username = (String) redisTemplate.opsForHash().get(session.getId(), "username");

        createRequest.setProjectAdmin(username);

        projectService.createProject(createRequest);

        return "redirect:list";
    }

    @PostMapping("/{id}/members")
    public String addMembers(@PathVariable Long id,
                                   @RequestBody AddProjectMemeberRequest addRequest,
                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/projects/" + id + "/members";
        }

        projectService.addMembers(addRequest);

        return "redirect:/projects/" + id;
    }

    @GetMapping("/{id}/dormant")
    public String makeDormantProject(@PathVariable Long id, Principal principal) {

        projectService.makeRestProject(id);

        return "redirect:/projects/" + principal.getName();
    }

    @GetMapping("/{id}/end")
    public String makeEndProject(@PathVariable Long id, Principal principal) {

        projectService.makeTerminateProject(id);

        return "redirect:/projects/" + principal.getName();
    }
}
