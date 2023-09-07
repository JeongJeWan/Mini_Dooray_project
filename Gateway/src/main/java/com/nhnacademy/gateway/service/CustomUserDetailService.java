package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.dto.CustomUser;
import com.nhnacademy.gateway.dto.user.UserDto;
import com.nhnacademy.gateway.dto.user.UserLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpMethod.POST;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private static final String BASE_URL = "http://localhost:8282";

    private final RestTemplate restTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final String PATH = "/account/users/login";

        UserLoginDto userLogin = new UserLoginDto(username);

        log.info("login user : {}", username);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserLoginDto> httpEntity = new HttpEntity<>(userLogin, headers);

        ResponseEntity<UserDto> exchange = restTemplate.exchange(BASE_URL + PATH, POST, httpEntity, UserDto.class);

        if(Objects.equals(exchange.getStatusCode(), HttpStatus.BAD_REQUEST)) {
            throw new UsernameNotFoundException(username);
        }

        UserDto user = Optional.ofNullable(exchange.getBody()).orElseThrow(() -> new UsernameNotFoundException(username));

        log.info("login user id : {}", user.getUserId());
        log.info("login status : {}", exchange.getStatusCode());

        return new CustomUser(user, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
