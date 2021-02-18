package com.xuetang9.qingying.service.impl;

import com.xuetang9.qingying.domain.Category;
import com.xuetang9.qingying.mapper.CategoryMapper;
import com.xuetang9.qingying.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/15 19:24
 * @copyright 老九学堂
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = {Exception.class})
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listAll() {
        return categoryMapper.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    @Override
    public boolean save(Category category) {
        return categoryMapper.insert(category) > 0;
    }

    @Override
    public Category searchById(int id) {
        Category category = new Category();
        category.setId(id);
        return categoryMapper.selectByPrimaryKey(category);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    @Override
    public boolean update(Category category) {
        return categoryMapper.updateByPrimaryKey(category) > 0;
    }

    @Override
    public List<Category> listAllParent() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("isLeaf",true);
        return categoryMapper.selectByExample(example);
    }

}
