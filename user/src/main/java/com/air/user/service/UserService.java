package com.air.user.service;

import com.air.user.dto.request.LogInRequest;
import com.air.user.dto.request.SignUpRequest;
import com.air.user.dto.request.UpdateNameRequest;
import com.air.user.dto.request.UpdatePasswordRequest;
import com.air.user.dto.response.LoginResponse;
import com.air.user.dto.response.UserInfoResponse;

public interface UserService {
    void signup(SignUpRequest signUpRequest);
    LoginResponse login(LogInRequest logInRequest);
    String updateName(UpdateNameRequest updateNameRequest, String token);
    String updatePassword(UpdatePasswordRequest updatePasswordRequest, String token);
    void disableUser(String token);
    String reissueToken(String token);
    UserInfoResponse getUserInfo(Integer userId, String token);
}
