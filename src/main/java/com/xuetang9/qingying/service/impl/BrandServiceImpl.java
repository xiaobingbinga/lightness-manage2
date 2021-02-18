package com.xuetang9.qingying.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuetang9.qingying.domain.Brand;
import com.xuetang9.qingying.mapper.BrandMapper;
import com.xuetang9.qingying.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/16 9:41
 * @copyright 老九学堂
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout = 5,readOnly = true,rollbackFor = {Exception.class})
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> listByPage(int pageNum, int pageSize, Brand condition) {
        // 使用分页插件
        Page<Brand> page = PageHelper.startPage(pageNum, pageSize);
        brandMapper.select(condition);
        return page;
    }

    @Override
    public List<Brand> listByPageAndFilter(int pageNum, int pageSize, String filterWords) {
        // 使用分页插件
        Page<Brand> page = PageHelper.startPage(pageNum, pageSize);
        // 调用Mapper的Example查询
        // 构建查询条件
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        // 判断参数
        if (criteria != null && !filterWords.isEmpty()){
            String value = "%" + filterWords + "%";
            criteria.orLike("name",value);
            criteria.orLike("letter",value);
        }
        // 执行查询
        brandMapper.selectByExample(example);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    @Override
    public boolean save(Brand brand) {
        return brandMapper.insert(brand) > 0;
    }

    @Override
    public List<Brand> listByFilterWords(String filterWords) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("letter",filterWords);
        criteria.orLike("name","%" + filterWords + "%");

        return brandMapper.selectByExample(example);
    }

    @Override
    public List<Brand> listByCategoryId(int categoryId) {
        return brandMapper.selectByCategoryId(categoryId);
    }
}
