package com.sit.jbc.domain.dto.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;

@Getter
@Setter

public class SessionUser {
    Long userId;
    String username;
    HashMap<String, AccessPermission> accessUrls;
    String menuHtml;

    public AccessPermission getCurrentPermissions(){
        String path = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri().getPath();
        AccessPermission permissions = null;
        if(accessUrls != null)
            permissions = getAccessUrls().get(path);
        else
            permissions = new AccessPermission(0L, 0L, 0L);
        return permissions;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SessionUser) {
            return username.equals( ((SessionUser) obj).getUsername() );
        }
        return false;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

}