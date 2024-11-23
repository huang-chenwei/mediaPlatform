package com.mediaplatform.service;

import com.mediaplatform.bean.dto.LoginDTO;
import com.mediaplatform.bean.dto.UserDTO;
import com.mediaplatform.bean.pojo.User;
import com.mediaplatform.exception.CustomException;
import com.mediaplatform.repository.UserRepository;
import com.mediaplatform.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(UserDTO userDto) {
        if (userRepository.existsByPhone(userDto.getPhone())) {
            throw new CustomException("手機號碼已被註冊");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new CustomException("電子郵件已被註冊");
        }

        String salt = generateSalt();
        String hashedPassword = passwordEncoder.encode(userDto.getPassword() + salt);

        Integer userId = userRepository.createUser(
            userDto.getUserName(),
            userDto.getEmail(),
            userDto.getPhone(),
            hashedPassword,
            salt,
            userDto.getBiography(),
            userDto.getCoverImage()
        );

        if (userId == null) {
            throw new CustomException("註冊失敗");
        }
    }

    public String login(LoginDTO loginDto) {
        User user = userRepository.findByPhone(loginDto.getPhone())
            .orElseThrow(() -> new CustomException("用戶不存在"));

        if (!passwordEncoder.matches(loginDto.getPassword() + user.getSalt(), user.getPassword())) {
            throw new CustomException("密碼錯誤");
        }

        return jwtService.generateToken(user.getPhone());
    }

    private String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}