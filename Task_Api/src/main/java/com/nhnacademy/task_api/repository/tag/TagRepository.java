package com.nhnacademy.task_api.repository.tag;

import com.nhnacademy.task_api.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findTagsByProject_Id(Long projectId);
}
