package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.dto.security.AccessPermission;
import com.sit.jbc.domain.dto.security.SessionUser;
import com.sit.jbc.util.WhitelistedURLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.Map;

public class CustomAuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    HttpSession httpSession;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //checking if it's an ajax call
        String requestedWith = request.getHeader("X-Requested-With");
        Boolean isAjax = requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;

        //checking for user session
        SessionUser user = null;
        try {
            user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e){

        }
        String path = new URL(request.getRequestURL().toString()).getPath();

        // if the user is logged in and the request is from browser GET hit
        if(user != null
                && !isAjax
                && "GET".equals(request.getMethod())
                && !path.contains("/error")
                && !path.contains("/images")) {

            AccessPermission urlFound = user.getAccessUrls().get(path);
            AccessPermission whiteUrlFound = WhitelistedURLs.getUrls().get(path);
            httpSession.setAttribute("userPermission", urlFound);

            if(urlFound == null) {
                if(whiteUrlFound == null) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.sendRedirect(request.getContextPath() + "/error/403");
                    return false;
                }
            }
        }
        return true;
    }
}