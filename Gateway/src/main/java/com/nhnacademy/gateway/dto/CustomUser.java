package com.nhnacademy.gateway.dto;

import com.nhnacademy.gateway.dto.user.UserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
public class CustomUser implements UserDetails {
    private final Long id;
    private final String userId;
    private final String userPassword;
    private final String userEmail;

    private final String status;

    private final List<? extends GrantedAuthority> authorities;

    public CustomUser(UserDto userDto, List<? extends GrantedAuthority> authorities) {
        this.id = userDto.getId();
        this.userId= userDto.getUserId();
        this.userPassword = userDto.getUserPassword();
        this.userEmail = userDto.getUserEmail();
        this.status = userDto.getStatus();
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
