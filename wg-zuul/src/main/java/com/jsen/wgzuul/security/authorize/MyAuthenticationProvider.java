package com.jsen.wgzuul.security.authorize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.Transient;
import java.util.List;

/**
 * @（#）:MyAuthenticationProvider.java
 * @description: 登录验证
 * @author: jsen 2018/8/28
 * @version: Version 1.0
 */
@Slf4j
public class MyAuthenticationProvider implements AuthenticationProvider {

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminAuthenticationToken token = (AdminAuthenticationToken) authentication;
        String username = token.getUsername();
        String password = token.getPrincipal().toString();
        UserDetails loadUser;
        try {
            loadUser = new UserDetailsImpl(username, password, this.getGrantedAuthorityList());
        } catch (UsernameNotFoundException notFound) {
            throw notFound;
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(
                    repositoryProblem.getMessage(), repositoryProblem);
        }
        if (loadUser == null) {
            log.error("UserDetailsService returned null, which is an interface contract violation.");
            throw new InternalAuthenticationServiceException("throw new InternalAuthenticationServiceException");
        }
        try {
            additionalAuthenticationChecks(loadUser, token);
        } catch (AuthenticationException exception) {
            log.error("Username and password are invalid.", exception);
            throw exception;
        }
        token.setPrincipal(loadUser);
        token.setDetails(loadUser);
        token.setAuthorities(loadUser.getAuthorities());
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    private void additionalAuthenticationChecks(UserDetails userDetails, AdminAuthenticationToken authentication)
            throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.debug("UserDetails userDetails, AdminAuthenticationToken authentication");
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        String authPassword = authentication.getCredentials().toString();
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String presentPassword = passwordEncoder.encode(authPassword);

        if (!passwordEncoder.matches(userDetails.getPassword(), presentPassword)) {
            log.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
    }

    @Transient
    private List<GrantedAuthority> getGrantedAuthorityList() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AdminAuthenticationToken.class);
    }
}
