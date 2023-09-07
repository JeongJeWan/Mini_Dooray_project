package com.nhnacademy.account_api.service;

import com.nhnacademy.account_api.dto.UserDto;
import com.nhnacademy.account_api.dto.UserIdLoginDto;
import com.nhnacademy.account_api.dto.UserSignUpDto;
import com.nhnacademy.account_api.entity.User;
import com.nhnacademy.account_api.exception.UserEmailNotFoundException;
import com.nhnacademy.account_api.exception.UserIdDuplicationException;
import com.nhnacademy.account_api.exception.UserNotFoundException;
import com.nhnacademy.account_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public void doSignUp(UserSignUpDto userSignUpDto) {
        if(userRepository.existsByUserId(userSignUpDto.getUserId())) {
            throw new UserIdDuplicationException(userSignUpDto.getUserId());
        }

        User user = User.builder()
                .userId(userSignUpDto.getUserId())
                .userPassword(userSignUpDto.getUserPassword())
                .userEmail(userSignUpDto.getUserEmail())
                .build();

        log.info("<<<<회원가입 성공>>>> = {}", user);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserDto doLogin(UserIdLoginDto loginDto) {
        User user = userRepository.findByUserId(loginDto.getUserId()).orElseThrow(UserNotFoundException::new);

        return new UserDto(user);
    }

    public void changeUserStatusLeave(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.changeLeaveUser();
    }

    public void changeUserStatusRest(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.changeRestUser();
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::new)
                .collect(toList());

    }

    @Transactional(readOnly = true)
    public UserDto findByUserByEmail(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(UserEmailNotFoundException::new);

        return new UserDto(user);
    }
}
