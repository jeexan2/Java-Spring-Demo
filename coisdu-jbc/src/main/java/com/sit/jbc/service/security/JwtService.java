package com.sit.jbc.service.security;

import com.sit.jbc.domain.dto.security.LoginUser;

public interface JwtService {
    public String getEncryptedToken(String userData);
    public String readEncryptedToken(String token) throws Exception;
}