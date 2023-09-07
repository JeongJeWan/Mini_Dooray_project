package com.nhnacademy.task_api.repository.tasktag;

import com.nhnacademy.task_api.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {
    List<TaskTag> findByTaskId(Long taskId);
}
