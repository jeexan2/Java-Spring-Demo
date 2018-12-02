package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {
    String curPassword;
    String newPassword;
    String newPasswordAgain;
}