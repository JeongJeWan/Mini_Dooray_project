package com.nhnacademy.task_api.service.impl;

import com.nhnacademy.task_api.dto.milestone.CreateMilestoneDto;
import com.nhnacademy.task_api.dto.milestone.MilestoneDto;
import com.nhnacademy.task_api.dto.milestone.ModifyMilestoneDto;
import com.nhnacademy.task_api.entity.Milestone;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.exception.MilestoneNotFoundException;
import com.nhnacademy.task_api.exception.ProjectNotFoundException;
import com.nhnacademy.task_api.repository.milestone.MilestoneRepository;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @Override
    public void createMilestone(CreateMilestoneDto createMilestone) {

        Project project = projectRepository.findById(createMilestone.getProjectId()).orElseThrow(ProjectNotFoundException::new);

        milestoneRepository.save(new Milestone(project, createMilestone));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MilestoneDto> findMilestoneByProjectId(Long projectId) {
        return milestoneRepository.findMilestoneByProject_Id(projectId)
                .stream()
                .map(MilestoneDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MilestoneDto findMilestone(Long id) {

        Milestone milestone = milestoneRepository.findById(id).orElseThrow(MilestoneNotFoundException::new);

        return new MilestoneDto(milestone);
    }

    @Override
    public void modifyMilestone(ModifyMilestoneDto modifyMilestone) {

        Milestone milestone = milestoneRepository.findById(modifyMilestone.getId()).orElseThrow(MilestoneNotFoundException::new);

        milestone.modifyMilestone(modifyMilestone);
    }

    @Override
    public void deleteMilestone(Long id) {
        Milestone milestone = milestoneRepository.findById(id).orElseThrow(MilestoneNotFoundException::new);

        milestoneRepository.delete(milestone);
    }
}
