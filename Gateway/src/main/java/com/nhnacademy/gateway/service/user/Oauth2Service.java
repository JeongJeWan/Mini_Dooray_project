package com.nhnacademy.gateway.service.user;

import com.nhnacademy.gateway.dto.CustomUser;
import com.nhnacademy.gateway.dto.oauth.AccessToken;
import com.nhnacademy.gateway.dto.oauth.EmailRequest;
import com.nhnacademy.gateway.dto.oauth.GithubProfile;
import com.nhnacademy.gateway.dto.oauth.OAuthToken;
import com.nhnacademy.gateway.dto.user.UserDto;
import com.nhnacademy.gateway.exception.UserNotFoundByEmailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpMethod.POST;

@Slf4j
@Service
@RequiredArgsConstructor
public class Oauth2Service {

    @Value("${github.client.id}")
    private String CLIENT_ID;

    @Value("${github.client.secret}")
    private String CLIENT_SECRET;

    private static final String BASE_URL = "http://localhost:8282";
    private static final String TOKEN_REQUEST_URL = "https://github.com/login/oauth/access_token";
    private static final String PROFILE_REQUEST_URL = "https://api.github.com/user";
    private static final String REDIRECT_URL = "http://localhost:8080/login/oauth2/code/github";

    private final RedisTemplate<String, String> redisTemplate;
    private final RestTemplate restTemplate;

    public String getRedirectUrl() {
        return "https://github.com/login/oauth/authorize?" +
                "client_id=" + CLIENT_ID + "&" +
                "redirect_uri=" + REDIRECT_URL;
    }

    public String getRedirectUrlWithState() {
        return "https://github.com/login/oauth/authorize?" +
                "client_id=" + CLIENT_ID + "&state=" + "any-string" +
                "redirect_uri=" + REDIRECT_URL;
    }

    public boolean verifyResidentByEmail(String email, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("verify email request : {}", email);

        final String PATH = "/account/users/email";

        EmailRequest emailRequest = new EmailRequest(email);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<EmailRequest> httpEntity = new HttpEntity<>(emailRequest, headers);

        log.info("httpEntity ><><>><><: {}", httpEntity.getBody());

        ResponseEntity<UserDto> userEntity = restTemplate.exchange(BASE_URL + PATH, POST, httpEntity, UserDto.class);

        UserDto userDto = Optional.ofNullable(userEntity.getBody()).orElseThrow(() -> new UserNotFoundByEmailException(email));

        CustomUser customUser = new CustomUser(userDto, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        setInRedis(request, response, getAuthentication(customUser));

        Authentication authentication = new UsernamePasswordAuthenticationToken(customUser, "USER_PASSWORD", customUser.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        log.info("response status : {}", userEntity.getStatusCode());
        log.info("user response : {}", userDto.getUserId());

        return true;

    }

    public GithubProfile getProfile(String code) {
        OAuthToken oAuthToken = getOAuthToken(code);

        ResponseEntity<GithubProfile> profileResponse = restTemplate.exchange(PROFILE_REQUEST_URL, HttpMethod.GET, getProfileRequest(oAuthToken), GithubProfile.class);

        return profileResponse.getBody();
    }

    public OAuthToken getOAuthToken(String code) {
        HttpEntity<AccessToken> codeRequest = getCodeRequest(code);
        ResponseEntity<OAuthToken> tokenResponse = restTemplate.exchange(TOKEN_REQUEST_URL, POST, codeRequest, OAuthToken.class);

        return tokenResponse.getBody();
    }

    private HttpEntity<MultiValueMap<String, String>> getProfileRequest(OAuthToken token) {
        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.add("Authorization", "token " + token.getAccessToken());
        return new HttpEntity<>(requestHeader);
    }

    private HttpEntity<AccessToken> getCodeRequest(String code) {
        AccessToken token = new AccessToken(CLIENT_ID, CLIENT_SECRET, code);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(token, headers);
    }

    public Authentication getAuthentication(UserDetails user) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);

        return authentication;
    }

    public void setInRedis(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        ArrayList<GrantedAuthority> authorities = new ArrayList<>(customUser.getAuthorities());

        HttpSession session = request.getSession(true);
        session.setAttribute("userId", customUser.getUserId());

        Cookie cookie = new Cookie("SESSION", session.getId());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 3);
        response.addCookie(cookie);

        redisTemplate.opsForHash().put(session.getId(), "username", customUser.getUserId());
        redisTemplate.opsForHash().put(session.getId(), "id", String.valueOf(customUser.getId()));
        redisTemplate.opsForHash().put(session.getId(), "authority", authorities.get(0).getAuthority());
        redisTemplate.boundHashOps(session.getId()).expire(Duration.ofDays(3));

        log.info("Oauth github login success user : {}", customUser.getUserId());
    }
}
