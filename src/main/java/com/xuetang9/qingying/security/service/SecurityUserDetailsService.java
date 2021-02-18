package com.xuetang9.qingying.security.service;

import com.xuetang9.qingying.domain.User;
import com.xuetang9.qingying.domain.UserRole;
import com.xuetang9.qingying.security.domain.SecurityUser;
import com.xuetang9.qingying.service.RoleService;
import com.xuetang9.qingying.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/31 9:43
 * @copyright 老九学堂
 */
@Component
@Slf4j
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 根据用户名查询用户信息
     * @param s 登录的账号
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("<--------------------------------->进入登录权限的校验");
        // 根据登录的用户名获取用户信息
        User user = userService.get(s);
        // 根据登录的用户信息查询出对应的角色
        List<UserRole> userRoles = roleService.listRolesByUserId(user.getId());
        // 设置用户拥有的角色身份信息
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 循环设置身份
        for (UserRole userRole: userRoles){
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRoleId().toString()));
        }
        // 创建一个SpringSecurity使用用户信息对象
        return new SecurityUser(user,grantedAuthorities);
    }
}
