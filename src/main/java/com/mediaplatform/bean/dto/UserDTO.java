package com.mediaplatform.bean.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class UserDTO {
  
    private String userName;
    private String email;
    @Pattern(regexp = "^[0-9]{10}$")
    private String phone;   
    private String password;
    private String coverImage;
    private String biography;
}