package com.jsen.wgzuul.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @（#）:MyBCryptPasswordEncoder.java
 * @description:
 * @author: jsen 2018/8/28
 * @version: Version 1.0
 */
public class MyBCryptPasswordEncoder extends BCryptPasswordEncoder {
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String presentedPassword = passwordEncoder.encode(encodedPassword);
        return passwordEncoder.matches(rawPassword, presentedPassword);
    }
}
