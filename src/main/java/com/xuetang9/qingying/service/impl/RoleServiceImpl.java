package com.xuetang9.qingying.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuetang9.qingying.domain.Role;
import com.xuetang9.qingying.domain.RoleResource;
import com.xuetang9.qingying.domain.UserRole;
import com.xuetang9.qingying.domain.dto.RoleResourceDTO;
import com.xuetang9.qingying.domain.dto.RoleResourceDTOS;
import com.xuetang9.qingying.mapper.RoleMapper;
import com.xuetang9.qingying.mapper.RoleResourceMapper;
import com.xuetang9.qingying.mapper.UserRoleMapper;
import com.xuetang9.qingying.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 21:01
 * @copyright 老九学堂
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = {Exception.class})
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> listByFilterWords(int pageNum,int pageSize,String filterWords) {

        Page<Role> page = PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if (filterWords != null && !filterWords.isEmpty()){
            criteria.andLike("name","%" + filterWords + "%");
        }
        roleMapper.selectByExample(example);
        return page;
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    @Override
    public void updateRoleResource(int roleId, RoleResourceDTOS dtos) {

        for (RoleResourceDTO roleResourceDTO : dtos.getRoleResourceDTOS()) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(roleResourceDTO.getResourceId());
            if (roleResourceDTO.getAddable()){
                roleResourceMapper.insert(roleResource);
            }else {
                roleResourceMapper.delete(roleResource);
            }
        }
    }

    @Override
    public List<UserRole> listRolesByUserId(int userId) {
        Example example = new Example(UserRole.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);

        return userRoleMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    @Override
    public boolean add(Role role) {
        role.setDeleted(false);
        return roleMapper.insert(role) > 0;
    }

    @Override
    public List<RoleResource> listRolesByUrl(String url) {
        return roleResourceMapper.selectByUrl(url);
    }
}
