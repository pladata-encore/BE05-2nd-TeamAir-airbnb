package com.air.user.controller;

import com.air.user.dto.request.LogInRequest;
import com.air.user.dto.request.SignUpRequest;
import com.air.user.dto.request.UpdateNameRequest;
import com.air.user.dto.request.UpdatePasswordRequest;
import com.air.user.dto.response.LoginResponse;
import com.air.user.dto.response.UserInfoResponse;
import com.air.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.signup(signUpRequest);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LogInRequest logInRequest) {
        return userService.login(logInRequest);
    }
    @PutMapping("/name")
    public String updateName(
            @RequestBody UpdateNameRequest updateNameRequest,
            @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        return userService.updateName(updateNameRequest, token);
    }
    @PutMapping("/password")
    public String updatePassword(
            @RequestBody UpdatePasswordRequest updatePasswordRequest,
            @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        return userService.updatePassword(updatePasswordRequest, token);
    }
    @DeleteMapping()
    public void disableUser(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        userService.disableUser(token);
    }
    @GetMapping("/token")
    public String reissueToken(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        return userService.reissueToken(token);
    }
    @GetMapping("/{id}")
    public UserInfoResponse getUserInfo(
            @PathVariable("id") Integer id,
            @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        return userService.getUserInfo(id, token);
    }



    @GetMapping("/test")
    public String test() {
        return "ttttttt";
    }
}
