package com.xuetang9.qingying.security.domain;

import org.springframework.security.core.GrantedAuthority;
import com.xuetang9.qingying.domain.User;

import java.util.Collection;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/31 9:50
 * @copyright 老九学堂
 */
public class SecurityUser extends org.springframework.security.core.userdetails.User {
    private User userInfo;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUser(User userInfo, Collection<? extends GrantedAuthority> authorities) {
        super(userInfo.getAccount(), userInfo.getPassword(), authorities);
        this.userInfo = userInfo;
    }

    public User getUserInfo(){
        return userInfo;
    }
}
