package com.sit.jbc.service.security.impl;


import com.sit.jbc.domain.dto.security.AccessPermission;
import com.sit.jbc.domain.dto.security.RolewiseOptionProcPojo;
import com.sit.jbc.domain.dto.security.SessionUser;
import com.sit.jbc.domain.entity.security.User;
import com.sit.jbc.repository.security.AuthRepository;
import com.sit.jbc.repository.security.OptionProcedureRepository;
import com.sit.jbc.service.security.AuthService;
import com.sit.jbc.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    AuthService authService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("here 2");
        String s = null;
        User user = null;
        try {
            s = jwtService.readEncryptedToken(authentication.getPrincipal().toString());
        }
        catch (Exception e){
            throw new BadCredentialsException("Bad token error::1");
        }

        String[] tokens = s.split("::");
        String password = authentication.getCredentials().toString();

        if(tokens.length < 2) {
            throw new BadCredentialsException("Bad token error::2");
        }
        else {
            user =  authRepository.findByUsernameAndOfficeCategory(tokens[0], Long.parseLong(tokens[1]));
            if (user == null) {
                throw new BadCredentialsException("User credentials error::1");
            }
            else {
                if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
                    throw new BadCredentialsException("User credentials error::2");
                }
            }
        }
        SessionUser sessionUser = configureUserSession(user);
        return new UsernamePasswordAuthenticationToken(sessionUser, password, null);
    }

    public SessionUser configureUserSession (User user){
        SessionUser sessionUser = new SessionUser();
        sessionUser.setUserId(user.getUserId());
        sessionUser.setUsername(user.getUsername());

        List<RolewiseOptionProcPojo> optionList = authService.getRolewiseOptionList(user.getRoleId());

        HashMap<String, AccessPermission> accessUrls = new HashMap<>();
        //role specific access
        for(RolewiseOptionProcPojo opt: optionList){
            accessUrls.put(opt.getSecOptionAccessUrl(),
                    new AccessPermission(opt.getCanAdd(), opt.getCanEdit(), opt.getCanDelete()));
        }

        sessionUser.setAccessUrls(accessUrls);
        sessionUser.setMenuHtml(authService.getMenuElementsHtml(optionList));

        return sessionUser;
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }




}