package com.xuetang9.qingying.service;

import com.xuetang9.qingying.domain.Resource;
import com.xuetang9.qingying.domain.RoleResource;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 11:42
 * @copyright 老九学堂
 */
public interface ResourceService {
    List<Resource> listAll();

    List<RoleResource> listResourceByRoleId(int roleId);

    List<Resource> listResourceByUserId(int userId);

    boolean checkResourceByUrlAndUserId(String url, int userId);
}
