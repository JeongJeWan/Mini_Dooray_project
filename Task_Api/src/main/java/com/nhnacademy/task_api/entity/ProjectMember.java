package com.nhnacademy.task_api.entity;

import com.nhnacademy.task_api.dto.project.AddProjectMemberDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Project_members")
@Getter
@NoArgsConstructor
@ToString
public class ProjectMember {

    @EmbeddedId
    private Pk id;

    @MapsId("projectId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Getter
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {

        @Column(name = "member_id", nullable = false)
        private Long memberId;

        @Column(name = "project_id", nullable = false)
        private Long projectId;

    }

    public ProjectMember(Project project, AddProjectMemberDto.MemberInfo info) {
        this.id = new Pk(project.getId(), info.getMemberId());
        this.project = project;
        this.userId = info.getUserId();
    }

    public ProjectMember(Project project, Long memberId, String userId) {
        this.id = new Pk(project.getId(), memberId);
        this.project = project;
        this.userId = userId;
    }
}
