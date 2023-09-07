package com.nhnacademy.task_api.repository.projectmember;

import com.nhnacademy.task_api.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {
    boolean existsById_memberId(Long memberId);
}
