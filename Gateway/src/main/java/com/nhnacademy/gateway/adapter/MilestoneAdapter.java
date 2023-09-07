package com.nhnacademy.gateway.adapter;

import com.nhnacademy.gateway.dto.milestone.CreateMilestoneRequest;
import com.nhnacademy.gateway.dto.milestone.MilestoneDto;
import com.nhnacademy.gateway.dto.milestone.ModifyMilestoneRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.nhnacademy.gateway.adapter.AdapterTemplate.*;
import static org.springframework.http.HttpMethod.GET;

@Component
@RequiredArgsConstructor
public class MilestoneAdapter {

    private static final String MILESTONE = "/task/milestones";
    private static final String REQUEST_URL = BASE_URL + MILESTONE;

    private final RestTemplate restTemplate;

    public void createMilestone(CreateMilestoneRequest createRequest) {

        create(restTemplate, MILESTONE, createRequest);
    }

    public MilestoneDto findMilestone(Long id) {
        final String PATH = "/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<MilestoneDto> exchange = restTemplate.exchange(REQUEST_URL + PATH, GET, httpEntity, MilestoneDto.class);

        verifyCode(exchange.getStatusCode());

        return exchange.getBody();
    }

    public List<MilestoneDto> findMilestoneListProjectId(Long projectId) {
        AdapterTemplate<List<MilestoneDto>> template = AdapterTemplate.of();

        return template.findAll(restTemplate, MILESTONE + "/project/" + projectId);
    }

    public void modifyMilestone(ModifyMilestoneRequest modifyRequest) {

        modify(restTemplate, MILESTONE, modifyRequest);
    }

    public void deleteMilestone(Long id) {

        delete(restTemplate, MILESTONE, id);
    }
}
