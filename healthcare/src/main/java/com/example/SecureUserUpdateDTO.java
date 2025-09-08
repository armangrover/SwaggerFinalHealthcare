package com.example;

import lombok.Data;

@Data
public class SecureUserUpdateDTO {
    private String oldPassword;
    private String newUsername;
    private String newPassword;
}
