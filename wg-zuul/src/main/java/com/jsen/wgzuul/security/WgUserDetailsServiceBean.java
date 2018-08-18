package com.jsen.wgzuul.security;

import com.jsen.wgzuul.service.SecRoleSV;
import com.jsen.wgzuul.service.SecUserSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @（#）:WgUserDetailsServiceBean.java
 * @description:
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */

@Component
public class WgUserDetailsServiceBean implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(WgUserDetailsServiceBean.class);

    @Autowired
    private SecUserSV secUserSV;

    @Autowired
    private SecRoleSV secRoleSV;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
//        SecUser secUser = secUserSV.findByLoginname(loginName);
//
//        if (secUser == null) {
//            throw new UsernameNotFoundException("用户名" + loginName + "不存在");
//        }
//        String password = secUser.getPassword();
//        logger.info(password);
//
//        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
//        Iterator<String> roles = secRoleSV.findRoleListByUserId(secUser.getId()).iterator();
//        while (roles.hasNext()) {
//            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(roles.next()));
//        }
//
//        return new User(loginName, password, simpleGrantedAuthorities);

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginName);
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名" + loginName + "不存在");
        }
        return userDetails;
    }
}
