package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.milestone.CreateMilestoneRequest;
import com.nhnacademy.gateway.dto.milestone.MilestoneDto;
import com.nhnacademy.gateway.dto.milestone.ModifyMilestoneRequest;
import com.nhnacademy.gateway.service.task.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects/{projectId}/milestones")
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/create")
    public String create(@PathVariable Long projectId,
                         @ModelAttribute("milestone") CreateMilestoneRequest createRequest,
                         Model model) {

        model.addAttribute("projectId", projectId);
        model.addAttribute("url", "create");

        return "milestone/milestone-form";
    }

    @PostMapping("/create")
    public String doCreateMilestone(@PathVariable Long projectId,
                                    @ModelAttribute("milestone") @Valid CreateMilestoneRequest createRequest,
                                    BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("url", "create");
            return "milestone/milestone-form";
        }

        milestoneService.createMilestone(createRequest);

        return "redirect:/projects/" + projectId + "/milestones";
    }

    @GetMapping("/{id}")
    public String findMilestone(@PathVariable Long id, Model model) {

        MilestoneDto milestone = milestoneService.findMilestone(id);

        model.addAttribute("milestone", milestone);

        return "milestone/milestone";
    }

    @GetMapping
    public String milestoneList(@PathVariable Long projectId, Model model) {


        List<MilestoneDto> milestones =
                milestoneService.findMilestonesByProjectId(projectId);

        model.addAttribute("projectId", projectId);
        model.addAttribute("milestones", milestones);

        return "milestone/list";
    }

    @GetMapping("/{id}/modify")
    public String modify(@PathVariable Long id, Model model) {


        model.addAttribute("milestone", milestoneService.findMilestone(id));
        model.addAttribute("url", "modify");

        return "milestone/milestone-form";
    }

    @PostMapping("/{id}/modify")
    public String doModify(@PathVariable Long projectId,
                                 @PathVariable Long id,
                                 @ModelAttribute("milestone") @Valid
                                 ModifyMilestoneRequest modifyRequest,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("url", "modify");
            return "milestone/milestone-form";
        }

        milestoneService.modifyMilestone(modifyRequest);

        return "redirect:/projects/" + projectId + "/milestones/" + id;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long projectId, @PathVariable Long id) {

        milestoneService.deleteMilestone(id);

        return "redirect:/projects/" + projectId + "/milestones";
    }
}












