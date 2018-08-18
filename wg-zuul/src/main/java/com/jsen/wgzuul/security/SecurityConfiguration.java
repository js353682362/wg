package com.jsen.wgzuul.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @（#）:SecurityConfiguration.java
 * @description: spring security的主过滤器
 *
 * AbstractSecurityWebApplicationInitializer
 * @author: jsen 2018/8/8
 * @version: Version 1.0
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private WgUserDetailsServiceBean wgUserDetailsServiceBean;


    /**
     * Autowired注解会在框架启动之后执行方法
     * Spring会先实例化所有Bean，然后根据配置进行扫描，当检测到@Autowired后进行注入，注入时调用这个方法
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(wgUserDetailsServiceBean);
    }

    /**
     * 定义内存用户管理
     *
     * @return
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String finalPassword = "{bcrypt}" + bCryptPasswordEncoder.encode("123456");

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user1").password(finalPassword).authorities("USER").build());
        manager.createUser(User.withUsername("user2").password(finalPassword).authorities("USER").build());
        return manager;
    }

    // password 方案一：明文存储，用于测试，不能用于生产
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    // password 方案二：用 BCrypt 对密码编码
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    /**
     * password 方案三：支持多种编码，通过密码的前缀区分编码方式,推荐
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 主过滤器  定义需要过滤的url
     * 次级过滤则通过oauth的ResourceServerConfigurer
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().failureForwardUrl("/login?error").permitAll();
        http.logout().permitAll();
        http.csrf().disable();
    }

    // 配置内存模式的用户
    /*
     * @Bean
     * @Override protected UserDetailsService userDetailsService(){
     * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
     * manager.createUser(User.withUsername("test").password("123").authorities(
     * "USER").build());
     * manager.createUser(User.withUsername("test 1").password("123").authorities(
     * "USER").build()); return manager; }
     */

    /**
     * 需要配置这个支持password模式
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
