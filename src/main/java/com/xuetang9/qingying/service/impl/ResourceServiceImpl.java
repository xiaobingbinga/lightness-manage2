package com.xuetang9.qingying.service.impl;

import com.xuetang9.qingying.domain.Resource;
import com.xuetang9.qingying.domain.RoleResource;
import com.xuetang9.qingying.mapper.ResourceMapper;
import com.xuetang9.qingying.mapper.RoleResourceMapper;
import com.xuetang9.qingying.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 11:42
 * @copyright 老九学堂
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout = 5,readOnly = true,rollbackFor = {Exception.class})
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public List<Resource> listAll() {
        return resourceMapper.selectAll();
    }

    @Override
    public List<RoleResource> listResourceByRoleId(int roleId) {
        Example example = new Example(RoleResource.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",roleId);

        return roleResourceMapper.selectByExample(example);
    }

    @Override
    public List<Resource> listResourceByUserId(int userId) {
        return resourceMapper.selectResourceByUserId(userId);
    }

    @Override
    public boolean checkResourceByUrlAndUserId(String url, int userId) {
        return resourceMapper.selectCountByUrlAndUserId(url,userId) > 0;
    }
}
