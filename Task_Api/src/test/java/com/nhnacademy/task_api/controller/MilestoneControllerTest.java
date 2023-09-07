package com.nhnacademy.task_api.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task_api.dto.milestone.CreateMilestoneDto;
import com.nhnacademy.task_api.dto.milestone.MilestoneDto;
import com.nhnacademy.task_api.dto.milestone.ModifyMilestoneDto;
import com.nhnacademy.task_api.entity.Milestone;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.service.MilestoneService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MilestoneController.class)
class MilestoneControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private MilestoneService milestoneService;

    @Test
    @DisplayName("마일스톤 생성")
    void testCreateMilestone() throws Exception {
        CreateMilestoneDto createMilestoneDto = new CreateMilestoneDto();
        ReflectionTestUtils.setField(createMilestoneDto,"projectId",1L);
        ReflectionTestUtils.setField(createMilestoneDto,"name","test milestone name");
        ReflectionTestUtils.setField(createMilestoneDto,"createdAt", LocalDate.now());

        String createMilestoneDtoJson = mapper.writeValueAsString(createMilestoneDto);

        mockMvc.perform(post("/task/milestones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createMilestoneDtoJson))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("프로젝트의 마일스톤 조회")
    void testFindMilestoneByProjectId() throws Exception {
        Long projectId = 1L;
        when(milestoneService.findMilestoneByProjectId(projectId)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/task/milestones/project/{projectId}",projectId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("마일스톤 조회")
    void testFindMilestone() throws Exception {
        Long id = 1L;
        Milestone mileStone = spy(new Milestone());
        when(mileStone.getProject()).thenReturn(new Project());
        MilestoneDto milestoneDto = new MilestoneDto(mileStone);

        when(milestoneService.findMilestone(id)).thenReturn(milestoneDto);

        mockMvc.perform(get("/task/milestones/{id}",id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("마일스톤 수정")
    void testModifyMilestone() throws Exception{
        ModifyMilestoneDto modifyMilestoneDto = new ModifyMilestoneDto();
        ReflectionTestUtils.setField(modifyMilestoneDto,"id",1L);
        ReflectionTestUtils.setField(modifyMilestoneDto,"name","test milestone name");
        ReflectionTestUtils.setField(modifyMilestoneDto,"createdAt",LocalDate.now());

        String modifyMilestoneDtoJson = mapper.writeValueAsString(modifyMilestoneDto);

        doNothing().when(milestoneService).modifyMilestone(any(ModifyMilestoneDto.class));

        mockMvc.perform(put("/task/milestones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyMilestoneDtoJson))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("마일스톤 삭제")
    void testDeleteMilestone() throws Exception{
        doNothing().when(milestoneService).deleteMilestone(anyLong());

        mockMvc.perform(delete("/task/milestones/{id}",1L))
                .andExpect(status().isNoContent());
    }
}