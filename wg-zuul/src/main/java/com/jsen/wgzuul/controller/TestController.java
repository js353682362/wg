package com.jsen.wgzuul.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @（#）:TestController.java
 * @description:
 * @author: jsen 2018/8/18
 * @version: Version 1.0
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "test oauth2 success";
    }
}
