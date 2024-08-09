package com.financeapp.api.controller.common;

import com.financeapp.api.domain.user.UserAccount;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    private UserAccount getAuthenticatedUser(){
        Object principal = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (principal instanceof UserAccount) ? (UserAccount) principal : null;
    }

    protected UserAccount getAuthenticatedUserOrError(){
        UserAccount user = getAuthenticatedUser();
        if (user == null){
            throw new RuntimeException("Not authenticated");
        }
        return user;
    }

}
