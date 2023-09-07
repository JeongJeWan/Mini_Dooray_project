package com.nhnacademy.task_api.controller;

import com.nhnacademy.task_api.dto.milestone.CreateMilestoneDto;
import com.nhnacademy.task_api.dto.milestone.MilestoneDto;
import com.nhnacademy.task_api.dto.milestone.ModifyMilestoneDto;
import com.nhnacademy.task_api.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @PostMapping
    public ResponseEntity<Void> createMilestone(@RequestBody CreateMilestoneDto createMilestone) {

        milestoneService.createMilestone(createMilestone);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<MilestoneDto>> findMilestoneByProjectId(@PathVariable("projectId") Long projectId) {
        List<MilestoneDto> milestoneDtoList = milestoneService.findMilestoneByProjectId(projectId);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(milestoneDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilestoneDto> findMilestone(@PathVariable("id") Long id) {
        MilestoneDto milestone = milestoneService.findMilestone(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON).body(milestone);
    }

    @PutMapping
    public ResponseEntity<Void> modifyMilestone(@RequestBody ModifyMilestoneDto modifyMilestone) {

        milestoneService.modifyMilestone(modifyMilestone);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable("id") Long id) {
        milestoneService.deleteMilestone(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
