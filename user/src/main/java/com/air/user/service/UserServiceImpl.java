package com.air.user.service;

import com.air.user.api.ApiRoom;
import com.air.user.config.JwtTokenUtils;
import com.air.user.config.TokenInfo;
import com.air.user.dto.request.LogInRequest;
import com.air.user.dto.request.SignUpRequest;
import com.air.user.dto.request.UpdateNameRequest;
import com.air.user.dto.request.UpdatePasswordRequest;
import com.air.user.dto.response.LoginResponse;
import com.air.user.dto.response.UserInfoResponse;
import com.air.user.exception.DuplicateUserException;
import com.air.user.exception.IncorrectPasswordException;
import com.air.user.exception.LoginFailedException;
import com.air.user.global.domain.entity.User;
import com.air.user.global.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final ApiRoom apiRoom;

    //회원가입
    @Override
    public void signup(SignUpRequest signUpRequest) {
        //해당 이메일로 가입한 아이디가 있는지 확인
        List<User> users = userRepository.findByEmail(signUpRequest.email());
        if (!users.isEmpty()) throw new DuplicateUserException();
        //비밀번호 암호화
        String encodedPW = passwordEncoder.encode(signUpRequest.password());
        //DB에 유저 데이터 저장
        userRepository.save(signUpRequest.toEntity(encodedPW));
    }
    //로그인
    @Override
    public LoginResponse login(LogInRequest logInRequest) {
        //DB에서 입력받은 이메일과 일치하는 유저 조회
        List<User> users = userRepository.findByEmail(logInRequest.email());
        //받아온 유저목록이 비어(아이디가 틀림)있거나 입력받은 비밀번호와 DB의 비밀번호가 다를 경우 에러 반환
        if (users.isEmpty() ||
            !passwordEncoder.matches(logInRequest.password(), users.get(0).getPassword())
        ) throw new LoginFailedException();
        //로그인 성공 시 토큰 생성 후 리턴
        return new LoginResponse(users.get(0).getUserName(), jwtTokenUtils.createToken(users.get(0)));
    }
    //이름 변경
    @Override
    @Transactional
    public String updateName(UpdateNameRequest updateNameRequest, String token) {
        //입력받은 토큰에서 ID값 추출
        Integer userId = jwtTokenUtils.parseToken(token).id();
        //DB에서 유저 조회
        User user = userRepository.findByIdAndUserDisableFalse(userId);
        //유저 이름 변경
        user.setUserName(updateNameRequest.userName());
        //변경된 이름 기준으로 토큰 재발급
        return jwtTokenUtils.createToken(user);
    }
    //비밀번호 변경
    @Override
    @Transactional
    public String updatePassword(UpdatePasswordRequest updatePasswordRequest, String token) {
        Integer userId = jwtTokenUtils.parseToken(token).id();
        User user = userRepository.findByIdAndUserDisableFalse(userId);
        if (!passwordEncoder.matches(updatePasswordRequest.oldPassword(), user.getPassword())) throw new IncorrectPasswordException();
        user.setPassword(passwordEncoder.encode(updatePasswordRequest.newPassword()));
        return jwtTokenUtils.createToken(user);
    }
    //유저 비활성화
    @Override
    @Transactional
    public void disableUser(String token) {
        Integer userId = jwtTokenUtils.parseToken(token).id();
        User user = userRepository.findByIdAndUserDisableFalse(userId);
        user.setUserDisable(true);
        user.setEmail(null);
        apiRoom.disableRoom(userId);
    }
    //토큰 재발급
    @Override
    public String reissueToken(String token) {
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        User user = userRepository.findByIdAndUserDisableFalse(tokenInfo.id());
        if(user == null || !tokenInfo.userName().equals(user.getUserName())) throw new RuntimeException();
        return jwtTokenUtils.createToken(user);
    }
    //유저정보 조회
    @Override
    public UserInfoResponse getUserInfo(Integer userId, String token) {
        User user = userRepository.findByIdAndUserDisableFalse(userId);
        boolean authority = userId.equals(jwtTokenUtils.parseToken(token).id());
        return new UserInfoResponse(authority, user.getUserName());
    }
}
