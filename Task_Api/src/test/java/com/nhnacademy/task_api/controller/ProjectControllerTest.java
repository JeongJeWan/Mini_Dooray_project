package com.nhnacademy.task_api.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task_api.dto.project.AddProjectMemberDto;
import com.nhnacademy.task_api.dto.project.CreatedProjectDto;
import com.nhnacademy.task_api.service.ProjectService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ProjectService projectService;
    @Test
    @DisplayName("프로젝트 생성")
    void testCreateProject() throws Exception{
        CreatedProjectDto createdProjectDto = new CreatedProjectDto();
        ReflectionTestUtils.setField(createdProjectDto,"projectAdmin","marco");
        ReflectionTestUtils.setField(createdProjectDto,"name","project1");
        ReflectionTestUtils.setField(createdProjectDto,"content","testcontents");
        String createdProjectDtoJson = mapper.writeValueAsString(createdProjectDto);
        mockMvc.perform(post("/task/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createdProjectDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("프로젝트 멤버 추가")
    void testAddMembers() throws Exception{
        List<AddProjectMemberDto.MemberInfo> memberInfoList = new ArrayList<>();
        for(int i = 0;i <= 10; i++){
            AddProjectMemberDto.MemberInfo memberInfo = new AddProjectMemberDto.MemberInfo();
            ReflectionTestUtils.setField(memberInfo,"memberId",(long) i);
            ReflectionTestUtils.setField(memberInfo,"userId","user"+i);
            memberInfoList.add(memberInfo);
        }
        AddProjectMemberDto addProjectMemberDto = new AddProjectMemberDto();
        ReflectionTestUtils.setField(addProjectMemberDto,"id",1L);
        ReflectionTestUtils.setField(addProjectMemberDto,"memberInfoList",memberInfoList);
        String addProjectMemberDtoJson = mapper.writeValueAsString(addProjectMemberDto);

        mockMvc.perform(post("/task/projects/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addProjectMemberDtoJson))
                .andExpect(status().isCreated())
                .andDo(print());
    }


    //findProject메소드 검증 필요


    @Test
    @DisplayName("멤버 소속 프로젝트 조회")
    void testProjectByMemberId() throws Exception{
        when(projectService.findProjectByUserId(anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/task/projects/members/{userId}","marco")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    @DisplayName("프로젝트 종료시키기")
    void testChangeToTerminateProject() throws Exception{
        doNothing().when(projectService).changeToTerminateProject(anyLong());
        mockMvc.perform(put("/task/projects/{id}/terminate",1L))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("프로젝트 휴면")
    void testChangeToRestProject() throws Exception{
        doNothing().when(projectService).changeToRestProject(anyLong());
        mockMvc.perform(put("/task/projects/{id}/rest",1L))
                .andExpect(status().isOk());
    }
}