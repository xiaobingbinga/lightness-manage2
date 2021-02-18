package com.xuetang9.qingying.config;

import com.xuetang9.qingying.security.SecurityAccessDecisionManager;
import com.xuetang9.qingying.security.filter.TokenAuthenticationFilter;
import com.xuetang9.qingying.security.handler.SecurityAccessDeniedHandler;
import com.xuetang9.qingying.security.service.SecurityUserDetailsService;
import com.xuetang9.qingying.service.RoleService;
import com.xuetang9.qingying.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/31 9:19
 * @copyright 老九学堂
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SecurityAccessDecisionManager accessDecisionManager;

    @Autowired
    private SecurityAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 配置加密的算法
     * @return
     */
    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 配置跨域请求
                .cors()
                .and()
                // 配置跨站点伪造请求的禁用
                .csrf().disable()
                // 配置权限校验的请求的详细设置
                .authorizeRequests()
                // 跨域请求的前置请求全部允许
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 配置不需要校验的地址
                .antMatchers("/api/login").permitAll()
                // 配置其余的任意请求都需要权限校验
                .anyRequest().authenticated()
                // 添加自定义的权限鉴定器
                .accessDecisionManager(accessDecisionManager)
                .and()
                // 配置没有权限时的异常处理
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                // 配置表单登录
                .formLogin().loginProcessingUrl("/api/login")
                // 配置登录请求的参数
                .usernameParameter("account")
                .passwordParameter("password")
                // 配置登录授权成功后的处理
                .successHandler(authenticationSuccessHandler)
                // 配置登录授权失败后的处理
                .failureHandler(authenticationFailureHandler)
                .and()
                .addFilter(new TokenAuthenticationFilter(authenticationManager(),roleService,userService));

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置具体的校验方式
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder());
    }

}
