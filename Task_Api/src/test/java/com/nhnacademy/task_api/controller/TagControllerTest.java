package com.nhnacademy.task_api.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task_api.dto.tag.CreateTagDto;
import com.nhnacademy.task_api.dto.tag.ModifyTagDto;
import com.nhnacademy.task_api.service.TagService;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TagController.class)
class TagControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private TagService tagService;

    @Test
    @DisplayName("태그 생성")
    void testCreateTag() throws Exception {
        CreateTagDto createTagDto = new CreateTagDto();
        ReflectionTestUtils.setField(createTagDto, "projectId", 1L);
        ReflectionTestUtils.setField(createTagDto, "name", "test name");

        String createTagDtoJson = mapper.writeValueAsString(createTagDto);

        mockMvc.perform(post("/task/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createTagDtoJson))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("프로젝트에 속한 태그 조회")
    void testFindTagsByProjectId() throws Exception {
        Long projectId = 1L;
        when(tagService.findTagByProjectId(projectId)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/task/tags/project/{projectId}",projectId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("태그 수정")
    void testModifyTag() throws Exception {
        ModifyTagDto modifyTagDto = new ModifyTagDto();
        ReflectionTestUtils.setField(modifyTagDto,"id",1L);
        ReflectionTestUtils.setField(modifyTagDto,"name","test tag name");

        String modifyTagDtoJson = mapper.writeValueAsString(modifyTagDto);

        mockMvc.perform(put("/task/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyTagDtoJson))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("태그 삭제")
    void testDeleteTag() throws Exception {
        Long tagId = 1L;
        doNothing().when(tagService).deleteTag(tagId);

        mockMvc.perform(delete("/task/tags/{id}",tagId))
                .andExpect(status().isNoContent());
    }
}