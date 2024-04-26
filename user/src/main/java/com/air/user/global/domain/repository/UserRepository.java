package com.air.user.global.domain.repository;

import com.air.user.global.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);
    User findByIdAndUserDisableFalse(Integer id);
}
