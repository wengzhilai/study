package com.wzl.commons.model;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class TokenUser extends SecurityProperties.User {
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int userId;
}
