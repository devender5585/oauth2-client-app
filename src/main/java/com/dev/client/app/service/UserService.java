package com.dev.client.app.service;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.dev.client.app.entity.User;

@Service
public class UserService {

    public User createUser(OidcUser oidcUser) {
        User user = new User();
        user.setName(oidcUser.getAttribute("name"));
        user.setEmail(oidcUser.getAttribute("email"));
        return user;
    }
}
