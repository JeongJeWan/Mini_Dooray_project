package com.nhnacademy.gateway.adapter;

import com.nhnacademy.gateway.dto.tag.CreateTagRequest;
import com.nhnacademy.gateway.dto.tag.ModifyTagRequest;
import com.nhnacademy.gateway.dto.tag.TagDto;
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
public class TagAdapter {

    private static final String TAGS = "/task/tags";
    private static final String REQUEST_URL = BASE_URL + TAGS;
    private final RestTemplate restTemplate;

    public void createTag(CreateTagRequest createRequest) {

        create(restTemplate, TAGS, createRequest);
    }

    public List<TagDto> findTagsByProjectId(Long projectId) {

        AdapterTemplate<List<TagDto>> template = AdapterTemplate.of();
        return template.findAll(restTemplate, TAGS + "/project/" + projectId);
    }

    public TagDto findById(Long id) {

        final String PATH = "/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<TagDto> exchange = restTemplate.exchange(REQUEST_URL + PATH, GET, httpEntity, TagDto.class);

        verifyCode(exchange.getStatusCode());

        return exchange.getBody();
    }

    public void modifyTag(ModifyTagRequest modifyRequest) {

        modify(restTemplate, TAGS, modifyRequest);
    }

    public void deleteTag(Long id) {

        delete(restTemplate, TAGS, id);
    }
}
