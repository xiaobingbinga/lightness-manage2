package com.xuetang9.qingying.service;

import com.xuetang9.qingying.domain.Role;
import com.xuetang9.qingying.domain.RoleResource;
import com.xuetang9.qingying.domain.UserRole;
import com.xuetang9.qingying.domain.dto.RoleResourceDTOS;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 21:00
 * @copyright 老九学堂
 */
public interface RoleService {
    List<Role> listByFilterWords(int pageNum,int pageSize,String filterWords);

    List<Role> listAll();

    void updateRoleResource(int roleId, RoleResourceDTOS dtos);

    List<UserRole> listRolesByUserId(int userId);

    boolean add(Role role);

    List<RoleResource> listRolesByUrl(String url);
}
