package com.jsen.wgzuul.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @（#）:UserDetailsImpl.java
 * @description:
 * @author: jsen 2018/8/28
 * @version: Version 1.0
 */
public class UserDetailsImpl extends User {

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
