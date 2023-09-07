package com.nhnacademy.task_api.repository.milestone;

import com.nhnacademy.task_api.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findMilestoneByProject_Id(Long projectId);
}
