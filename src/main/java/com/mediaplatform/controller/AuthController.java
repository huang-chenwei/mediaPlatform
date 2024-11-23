package com.mediaplatform.controller;

import com.mediaplatform.bean.dto.LoginDTO;
import com.mediaplatform.bean.dto.UserDTO;
import com.mediaplatform.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDto) {
        userService.register(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }
}