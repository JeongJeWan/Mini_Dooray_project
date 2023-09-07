package com.nhnacademy.account_api.repository;

import com.nhnacademy.account_api.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자 등록")
    void testUserRegister() {
        User user = User.builder()
                .userId("marco1")
                .userPassword("1234")
                .userEmail("marco1@nhnacademy.com")
                .build();

        userRepository.save(user);

        Optional<User> actual = userRepository.findByUserId(user.getUserId());

        assertThat(actual).isPresent();
    }

    @Test
    @DisplayName("이메일 통해 회원확인")
    void testFindUserByEmail(){
        User user = User.builder()
                .userId("marco1")
                .userPassword("1234")
                .userEmail("marco1@nhnacademy.com")
                .build();
        User savedUser = userRepository.save(user);
        Optional<User> emailUser = userRepository.findByUserEmail(savedUser.getUserEmail());
        assertThat(emailUser.get().getUserId()).isEqualTo(savedUser.getUserId());
    }

    @Test
    @DisplayName("회원 중복검사")
    void testUserDuplication(){
        User user = User.builder()
                .userId("marco1")
                .userPassword("1234")
                .userEmail("marco1@nhnacademy.com")
                .build();
        userRepository.save(user);
        boolean result = userRepository.existsByUserId(user.getUserId());
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("회원 휴면 처리")
    void testUserStatusRest(){
        User user = User.builder()
                .userId("marco1")
                .userPassword("1234")
                .userEmail("marco1@nhnacademy.com")
                .build();
        User savedUser = userRepository.save(user);
        savedUser.changeRestUser();

        User testUser = userRepository.findById(savedUser.getId()).get();
        assertThat(savedUser.getStatus()).isEqualTo(testUser.getStatus());
    }
    @Test
    @DisplayName("회원 탈퇴 처리")
    void testUserStatusLeave(){
        User user = User.builder()
                .userId("marco1")
                .userPassword("1234")
                .userEmail("marco1@nhnacademy.com")
                .build();
        User savedUser = userRepository.save(user);
        savedUser.changeLeaveUser();

        User testUser = userRepository.findById(savedUser.getId()).get();
        assertThat(savedUser.getStatus()).isEqualTo(testUser.getStatus());
    }
}