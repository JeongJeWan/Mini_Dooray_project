package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.oauth.GithubProfile;
import com.nhnacademy.gateway.exception.WrongEmailException;
import com.nhnacademy.gateway.service.user.Oauth2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class Oauth2Controller {

    private final Oauth2Service oauth2Service;

    @GetMapping("/github")
    public RedirectView githubLogin() {
        return new RedirectView(oauth2Service.getRedirectUrl());
    }

    @GetMapping("/oauth2/code/github")
    public String githubRedirect(@RequestParam("code") String code,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException, ServletException, WrongEmailException {

        GithubProfile profile = oauth2Service.getProfile(code);
        log.info("email:{}",profile.getEmail());
        boolean existUser = oauth2Service.verifyResidentByEmail(profile.getEmail(), request, response);

        if(!existUser) {
            throw new WrongEmailException();
        }

        return "redirect:/";

    }
}
