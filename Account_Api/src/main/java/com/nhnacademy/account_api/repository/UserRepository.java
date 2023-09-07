package com.nhnacademy.account_api.repository;

import com.nhnacademy.account_api.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserEmail(String email);
    boolean existsByUserId(String userId);

}
