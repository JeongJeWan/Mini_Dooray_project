package com.nhnacademy.account_api.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    private UserStatus status;

    @Builder
    public User(String userId, String userPassword, String userEmail) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.status = UserStatus.JOIN;
    }

    public void changeLeaveUser() {
        this.status = UserStatus.LEAVE;
    }
    public void changeRestUser() {
        this.status = UserStatus.REST;
    }
}
