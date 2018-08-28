package com.jsen.wgzuul.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * @（#）:WgAuthorizationServerConfigureer.java
 * @description: oauth的控制类
 * @author: jsen 2018/8/14
 * @version: Version 1.0
 */
@Configuration
@EnableAuthorizationServer
public class WgAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // 设置token失效时间
        defaultTokenServices.setAccessTokenValiditySeconds(20 * 60);
        // 设置refresh_token 失效时间
        defaultTokenServices.setRefreshTokenValiditySeconds(60 * 60);
        defaultTokenServices.setClientDetailsService(this.clientDetailsService());
        // 支持通过refresh_token刷新
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setRefreshTokenValiditySeconds(15 * 60);
        // token格式
        defaultTokenServices.setTokenEnhancer(this.jwtAccessTokenConverter());
        defaultTokenServices.setTokenStore(new MyRedisTokenStore(redisConnectionFactory));
        return defaultTokenServices;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        /*设置签名*/
        accessTokenConverter.setSigningKey("smallsnail");
        return accessTokenConverter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        password 方案一：明文存储，用于测试，不能用于生产
//        String finalSecret = "123456";
//        password 方案二：用 BCrypt 对密码编码
//        String finalSecret = new BCryptPasswordEncoder().encode("123456");
        //方案三：支持多种编码，通过密码的前缀区分编码方式


        /**
         *      备注：新版本的Spring Security要求必须为用户配置提供编码器，
         *      否则会报找不到相应的编码器错误。这里有个不是很重要的知识点，
         *      假如我们没有调用passwordEncoder方法为用户验证指明编码器，
         *      那么有一种替代方案，就是在密码前加"{noop}"等前缀，跟踪源码发现，
         *      框架会自动解析{}中的key去匹配相应的编码器。下面提供一个调试的图，可以了解下。
         */
//        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
//
//        //配置两个客户端，一个用于password认证一个用于client认证
//        clients.inMemory().withClient("client_1")
//                .resourceIds(WgResourceServerConfigurer.RESOURCE_ID)
//                //选择授权类型  -- 客户端类型
//                .authorizedGrantTypes("client_credentials", "refresh_token")
//                //表示权限范围
//                .scopes("select")
//                .authorities("oauth2")
//                .secret(finalSecret);
//
//        //定于client_2 认证模式为password
//        clients.inMemory().withClient("client_2")
//                .resourceIds(WgResourceServerConfigurer.RESOURCE_ID)
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("select")
//                .authorities("oauth2")
//                .secret(finalSecret);
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单认证
        security.allowFormAuthenticationForClients();
        super.configure(security);
    }

    /**
     * 配置token保存仓库
     * 解释不了  看代码
     *
     * @param authorizationServerEndpointsConfigurer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
        authorizationServerEndpointsConfigurer
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        authorizationServerEndpointsConfigurer.tokenServices(tokenServices());
    }
}
