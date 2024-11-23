package com.mediaplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.mediaplatform.bean.pojo.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhone(String phone);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    
    @Procedure(name = "sp_CreateUser")
    Integer createUser(
        @Param("UserName") String userName,
        @Param("Email") String email,
        @Param("Phone") String phone,
        @Param("Password") String password,
        @Param("Salt") String salt,
        @Param("Biography") String biography,
        @Param("CoverImage") String coverImage
    );
}