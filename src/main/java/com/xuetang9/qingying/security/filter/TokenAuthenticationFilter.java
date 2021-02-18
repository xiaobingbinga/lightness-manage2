package com.xuetang9.qingying.security.filter;

import com.xuetang9.qingying.domain.User;
import com.xuetang9.qingying.domain.UserRole;
import com.xuetang9.qingying.service.RoleService;
import com.xuetang9.qingying.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/31 10:39
 * @copyright 老九学堂
 */
//@Component
@Slf4j
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public TokenAuthenticationFilter(AuthenticationManager authenticationManager,RoleService roleService,UserService userService) {
        super(authenticationManager);
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 需要从请求的头中获取token
        String token = request.getHeader("token");
        log.debug("<------------------>执行过滤，request - " + request.getRequestURI());
        log.debug("<------------------>执行过滤，token - " + token);

        User user = userService.get(Integer.parseInt(token));
        List<UserRole> userRoles = roleService.listRolesByUserId(Integer.parseInt(token));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRoleId().toString()));
        }

        // 构建一个授权凭证
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getAccount(),null,grantedAuthorities));

        super.doFilterInternal(request, response, chain);
    }
}
