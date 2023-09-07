package com.nhnacademy.task_api.controller;

import com.nhnacademy.task_api.dto.tag.CreateTagDto;
import com.nhnacademy.task_api.dto.tag.ModifyTagDto;
import com.nhnacademy.task_api.dto.tag.TagDto;
import com.nhnacademy.task_api.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<Void> createTag(@RequestBody CreateTagDto createTag) {

        tagService.createTag(createTag);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TagDto>> findTagByProjectId(@PathVariable("projectId") Long projectId) {

        List<TagDto> tags = tagService.findTagByProjectId(projectId);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> findTag(@PathVariable("id") Long id) {
        TagDto tag = tagService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(tag);
    }

    @PutMapping
    public ResponseEntity<Void> modifyTag(@RequestBody ModifyTagDto modifyTag) {

        tagService.modifyTag(modifyTag);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("id") Long id) {

        tagService.deleteTag(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
