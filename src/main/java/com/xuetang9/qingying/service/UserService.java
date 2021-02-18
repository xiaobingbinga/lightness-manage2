package com.xuetang9.qingying.service;

import com.xuetang9.qingying.domain.User;
import com.xuetang9.qingying.domain.query.LoginQuery;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/14 16:58
 * @copyright 老九学堂
 */
public interface UserService {

    User login(LoginQuery query);

    List<User> listByFilterWords(int pageNum, int pageSize, String filterWords);

    void updateUserRole(int userId, int[] addRoleIds, int[] deleteRoleIds);

    User get(int userId);

    User get(String account);
}
