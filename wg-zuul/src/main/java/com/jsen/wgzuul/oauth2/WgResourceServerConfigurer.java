package com.jsen.wgzuul.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @（#）:WgResourceServerConfigurer.java
 * @description:
 *  主要功能 ： 类似于次级过滤器
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */
@Configuration
@EnableResourceServer
public class WgResourceServerConfigurer extends ResourceServerConfigurerAdapter{

    /**
     * resourceId
     */
    public static final String RESOURCE_ID = "order";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 如果关闭 stateless，则 accessToken 使用时的 session id 会被记录，后续请求不携带 accessToken 也可以正常响应
        resources.resourceId(RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * SessionCreationPolicy枚举类型
         * ALWAYS总是创建session
         * IF_REQUIRED 只会在需要的时候创建一个HttpSession
         * NEVER 不会创建HttpSession 如果它已经存在 将可以使用HttpSession
         * STATELESS Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        // 保险起见，防止被主过滤器链路拦截
        http.requestMatchers().antMatchers("/qq/**");
        http.authorizeRequests().anyRequest().authenticated();
    }
}