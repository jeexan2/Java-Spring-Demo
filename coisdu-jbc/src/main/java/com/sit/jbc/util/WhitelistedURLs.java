package com.sit.jbc.util;

import com.sit.jbc.domain.dto.security.AccessPermission;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class WhitelistedURLs {
    private static final Map<String, AccessPermission> whitelisteUrls = new HashMap<String, AccessPermission>();
    static {
        whitelisteUrls.put("/security/dashboard",
                new AccessPermission(1L, 1L, 1L));
        whitelisteUrls.put("/security/change_password",
                new AccessPermission(1L, 1L, 1L));
        whitelisteUrls.put("/security/test",
                new AccessPermission(1L, 1L, 1L));

    }

    public static Map<String, AccessPermission> getUrls(){
        return whitelisteUrls;
    }
}