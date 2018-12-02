package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.dto.security.LoginUser;
import com.sit.jbc.service.security.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.sit.jbc.util.JwtConstants.EXPIRE_MINUTES;
import static com.sit.jbc.util.JwtConstants.PLAIN_SECRET;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class JwtServiceImpl implements JwtService{
    //private final Long expireHours = EXPIRE_HOURS;
    private final Long expireMinutes = EXPIRE_MINUTES;

    private static final String plainSecret = PLAIN_SECRET;
    private static final String encodedSecret = Base64
            .getEncoder()
            .encodeToString(plainSecret.getBytes());

    @Autowired
    EncryptionService encryptionService;

    private Date getExpirationTime(){
        Date now = new Date();
        //Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
        Long expireInMilis = TimeUnit.MINUTES.toMillis(expireMinutes);
        return new Date(expireInMilis + now.getTime());
    }

    @Override
    public String getEncryptedToken(String userData) {
        //System.out.println("encodedSecret : " + encodedSecret);
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject("login_cred")
                .claim("user_data", encryptionService.encrypt(userData))
                .setIssuedAt(now)
                .setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS512, encodedSecret)
                .compact();
    }

    @Override
    public String readEncryptedToken(String token) throws Exception{
        Claims claims = Jwts.parser()
                .setSigningKey(encodedSecret)
                .parseClaimsJws(token)
                .getBody();

        String userData = claims.get("user_data").toString();

        return encryptionService.decrypt(userData);
    }
}