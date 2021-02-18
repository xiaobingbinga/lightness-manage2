package com.xuetang9.qingying.security;

import com.xuetang9.qingying.domain.RoleResource;
import com.xuetang9.qingying.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/31 10:47
 * @copyright 老九学堂
 */
@Component
@Slf4j
public class SecurityAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    private RoleService roleService;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        log.debug("<------------>进行权限鉴定");
        // 得到过滤器的委托对象
        FilterInvocation filterInvocation = (FilterInvocation) object;
        // 获取请求的url地址
        String requestUrl = filterInvocation.getRequestUrl();
        // 获取可访问的请求地址的角色
        List<RoleResource> roleResources = roleService.listRolesByUrl(requestUrl);
        // 循环比较角色是否相同
        for (RoleResource roleResource : roleResources) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                // 判断已授权的角色和访问资源的角色是否相等
                if (authority.getAuthority().equals(roleResource.getRoleId().toString())){
                    return;
                }
            }
        }
        // 对不起，没有权限
        throw new AccessDeniedException("对不起没有权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
