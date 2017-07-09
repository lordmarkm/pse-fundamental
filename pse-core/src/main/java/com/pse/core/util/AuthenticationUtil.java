package com.pse.core.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Mark Baldwin B. Martinez on 5 Jan 2017
 *
 */
public class AuthenticationUtil {

    public static boolean isAuthenticated() {
        return null != SecurityContextHolder.getContext().getAuthentication()
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public static String getLoggedInUsername() {
        if (null == SecurityContextHolder.getContext().getAuthentication()) {
            return "SYSTEM";
        } else {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
    }

}
