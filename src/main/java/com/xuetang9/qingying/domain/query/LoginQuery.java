package com.xuetang9.qingying.domain.query;

import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/14 16:59
 * @copyright 老九学堂
 */
public class LoginQuery implements Serializable {
    private String account;
    private String password;

    @Override
    public String toString() {
        return "LoginQuery{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
