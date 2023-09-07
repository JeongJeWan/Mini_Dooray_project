package com.nhnacademy.task_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task_api.dto.task.CreateTaskDto;
import com.nhnacademy.task_api.dto.task.ModifyTaskDto;
import com.nhnacademy.task_api.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private TaskService taskService;

    @Test
    @DisplayName("태스크 생성")
    void testCreateTask() throws Exception {
        CreateTaskDto createTaskDto = new CreateTaskDto();
        ReflectionTestUtils.setField(createTaskDto, "projectId", 1L);
        ReflectionTestUtils.setField(createTaskDto,"title","test title");
        ReflectionTestUtils.setField(createTaskDto,"content","test content");
        ReflectionTestUtils.setField(createTaskDto,"registerId","marco");

        String createTaskDtoJson = mapper.writeValueAsString(createTaskDto);

        mockMvc.perform(post("/task/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createTaskDtoJson))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("태스크 조회")
    void testFindTask() throws Exception {
        Long taskId = 1L;

        mockMvc.perform(get("/task/tasks/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    @DisplayName("프로젝트 태스크목록 조회")
    void testFindTaskList() throws Exception {
        Long projectId = 1L;

        mockMvc.perform(get("/task/tasks/project/{projectId}", projectId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    @DisplayName("태스크 수정")
    void testModifyTask() throws Exception {
        ModifyTaskDto modifyTaskDto = new ModifyTaskDto();
        ReflectionTestUtils.setField(modifyTaskDto, "taskId", 1L);
        ReflectionTestUtils.setField(modifyTaskDto, "title", "test title");
        ReflectionTestUtils.setField(modifyTaskDto, "content", "test content");

        String modifyTaskDtoJson = mapper.writeValueAsString(modifyTaskDto);

        mockMvc.perform(put("/task/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyTaskDtoJson))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    @DisplayName("태스크 삭제")
    void testDeleteTask() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/task/tasks/{id}",id))
                .andExpect(status().isNoContent());
    }


}