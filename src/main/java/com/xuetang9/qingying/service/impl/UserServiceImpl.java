package com.xuetang9.qingying.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuetang9.qingying.domain.User;
import com.xuetang9.qingying.domain.UserRole;
import com.xuetang9.qingying.domain.query.LoginQuery;
import com.xuetang9.qingying.mapper.UserMapper;
import com.xuetang9.qingying.mapper.UserRoleMapper;
import com.xuetang9.qingying.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/14 17:00
 * @copyright 老九学堂
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User login(LoginQuery query) {
        User condition = new User();
        BeanUtils.copyProperties(query,condition);
        System.out.println("condition:" + condition);
        return userMapper.selectOne(condition);
    }

    @Override
    public List<User> listByFilterWords(int pageNum, int pageSize, String filterWords) {
        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (filterWords != null && !filterWords.isEmpty()){
            filterWords = "%" + filterWords + "%";
            criteria.orLike("account",filterWords);
            criteria.orLike("phone",filterWords);
            criteria.orLike("email",filterWords);
        }
        userMapper.selectByExample(example);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    @Override
    public void updateUserRole(int userId, int[] addRoleIds, int[] deleteRoleIds) {
        // 添加角色和用户的关联
        if (addRoleIds != null){
            for (int addRoleId : addRoleIds){
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(addRoleId);
                userRoleMapper.insert(userRole);
            }
        }

        // 删除角色和用户的关联
        if (deleteRoleIds != null){
            for (int delRoleId : deleteRoleIds){
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(delRoleId);
                userRoleMapper.delete(userRole);
            }
        }
    }

    @Override
    public User get(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User get(String account) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account",account);
        return userMapper.selectOneByExample(example);
    }

}
