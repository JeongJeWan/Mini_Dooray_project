package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.tag.CreateTagRequest;
import com.nhnacademy.gateway.dto.tag.ModifyTagRequest;
import com.nhnacademy.gateway.dto.tag.TagDto;
import com.nhnacademy.gateway.service.task.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects/{projectId}/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/create")
    public String create(@PathVariable Long projectId,
                         @ModelAttribute("tag") CreateTagRequest createRequest,
                         Model model) {

        model.addAttribute("projectId", projectId);
        model.addAttribute("url", "create");

        return "tag/tag-form";
    }

    @PostMapping("/create")
    public String doCreate(@PathVariable Long projectId,
                                 @ModelAttribute("tag") @Valid CreateTagRequest createRequest,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("url", "create");
            return "tag/tag-form";
        }

        tagService.createTag(createRequest);

        return "redirect:/projects/" + projectId + "/tags";
    }

    @GetMapping("/{id}")
    public String findTag(@PathVariable Long projectId, @PathVariable Long id,
                          Model model) {

        model.addAttribute("tag", tagService.findTag(id));
        model.addAttribute("projectId", projectId);

        return "tag/tag";
    }

    @GetMapping
    public String tagList(@PathVariable Long projectId, Model model) {


        List<TagDto> tags = tagService.findTagsByProjectId(projectId);

        model.addAttribute("projectId", projectId);
        model.addAttribute("tags", tags);

        return "tag/list";
    }

    @GetMapping("/{id}/modify")
    public String modify(@PathVariable Long projectId, @PathVariable Long id,
                         Model model) {


        model.addAttribute("projectId", projectId);
        model.addAttribute("tag", tagService.findTag(id));
        model.addAttribute("url", "modify");

        return "tag/tag-form";
    }

    @PostMapping("/{id}/modify")
    public String doModify(@PathVariable Long projectId, @PathVariable Long id,
                                 @ModelAttribute("tag") @Valid ModifyTagRequest modifyRequest,
                                 BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("url", "modify");
            return "tag/tag-form";
        }

        tagService.modifyTag(modifyRequest);

        return "redirect:/projects/" + projectId + "/tags/" + id;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long projectId, @PathVariable Long id) {

        tagService.deleteTag(id);

        return "redirect:/projects/" + projectId + "/tags";
    }
}


















