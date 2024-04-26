package com.air.user.service;

import com.air.user.config.JwtTokenUtils;
import com.air.user.dto.request.LogInRequest;
import com.air.user.dto.request.UpdateNameRequest;
import com.air.user.dto.request.UpdatePasswordRequest;
import com.air.user.dto.response.UserInfoResponse;
import com.air.user.exception.IncorrectPasswordException;
import com.air.user.global.domain.entity.User;
import com.air.user.global.domain.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EntityManager em;

    private final String testEmail = "test@gmail.com";
    private final String testPassword = "password";
    private final String testUsername = "test";
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @BeforeEach
    void setUp() {
        userRepository.save(User
                            .builder()
                            .email(testEmail)
                            .password(passwordEncoder.encode(testPassword))
                            .userName(testUsername)
                            .build());
        setToken(userService.login(new LogInRequest(testEmail, testPassword)).token());
    }
    @Test
    void 이름_변경_성공() {
        //given
        UpdateNameRequest request = new UpdateNameRequest("test2");
        String token = getToken();
        //when
        String newToken = userService.updateName(request, token);
        em.flush();
        em.clear();
        //then
        Assertions.assertEquals("test2", jwtTokenUtils.parseToken(newToken).userName());
        Assertions.assertEquals("test2", userRepository.findById(jwtTokenUtils.parseToken(newToken).id()).get().getUserName());
    }
    @Test
    void 비밀번호_변경_성공() {
        //given
        UpdatePasswordRequest request = new UpdatePasswordRequest("password", "new password");
        String token = getToken();
        //when
        String newToken = userService.updatePassword(request, token);
        em.flush();
        em.clear();
        //then
        Assertions.assertEquals(jwtTokenUtils.parseToken(token).id(), jwtTokenUtils.parseToken(newToken).id());
        Assertions.assertTrue(passwordEncoder.matches("new password", userRepository.findById(jwtTokenUtils.parseToken(newToken).id()).orElse(null).getPassword()));
    }
    @Test
    void 비밀번호_변경_실패_잘못된_기존_비밀번호_입력() {
        //given
        UpdatePasswordRequest request = new UpdatePasswordRequest("password11", "new password");
        String token = getToken();
        //when & then
        Assertions.assertThrows(
                IncorrectPasswordException.class,
                () -> { userService.updatePassword(request, token); }
        );
    }
    @Test
    void 유저_비활성화(){
        //given
        String token = getToken();
        Integer userId = jwtTokenUtils.parseToken(token).id();
        //when
        userService.disableUser(token);
        em.flush();
        em.clear();
        //then
        Assertions.assertTrue(userRepository.findById(userId).orElse(null).isUserDisable());
        Assertions.assertNull(userRepository.findById(userId).orElse(null).getEmail());
    }
    @Test
    void 토큰_재발급(){
        //given
        String token = getToken();
        //when
        String newToken = userService.reissueToken(token);
        em.flush();
        em.clear();
        //then
        Assertions.assertEquals(jwtTokenUtils.parseToken(token).id(), jwtTokenUtils.parseToken(newToken).id());
        Assertions.assertEquals(jwtTokenUtils.parseToken(token).userName(), jwtTokenUtils.parseToken(newToken).userName());
    }
    @Test
    void 다른유저_정보_조회_성공(){
        //given
        String token = getToken();
        Integer searchUserId = 1;
        //when
        UserInfoResponse response = userService.getUserInfo(searchUserId, token);
        em.flush();
        em.clear();
        //then
        Assertions.assertFalse(response.authority());
    }
    @Test
    void 내_정보_조회_성공(){
        //given
        String token = getToken();
        Integer searchUserId = jwtTokenUtils.parseToken(token).id();
        //when
        UserInfoResponse response = userService.getUserInfo(searchUserId, token);
        em.flush();
        em.clear();
        //then
        Assertions.assertTrue(response.authority());
        Assertions.assertEquals(jwtTokenUtils.parseToken(token).userName(), response.userName());
    }
}