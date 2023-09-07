package com.nhnacademy.gateway.service.user;

import com.nhnacademy.gateway.dto.user.UserDto;
import com.nhnacademy.gateway.dto.user.UserSignUpDto;
import com.nhnacademy.gateway.exception.SignupFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private static final String BASE_URL = "http://localhost:8282";

    private final RestTemplate restTemplate;

    private final PasswordEncoder passwordEncoder;

    public void signUp(final UserSignUpDto signUp){

        signUp.passwordEncoder(passwordEncoder.encode(signUp.getUserPassword()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserSignUpDto> httpEntity = new HttpEntity<>(signUp, headers);

        ResponseEntity<Void> exchange = restTemplate.exchange(BASE_URL + "/account/users/signup", POST, httpEntity, Void.class);

        if (Objects.equals(exchange.getStatusCode(), BAD_REQUEST)) {
            throw new SignupFailException();
        }

        log.info("SIGN UP statusCODE: {}", exchange.getStatusCode());
    }

    public List<UserDto> findUsers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<List<UserDto>> exchange = restTemplate.exchange(BASE_URL + "/account/users", GET, httpEntity, new ParameterizedTypeReference<>() {});

        return exchange.getBody();
    }
}
