package com.xuetang9.qingying.service;

import com.xuetang9.qingying.domain.Category;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/15 19:24
 * @copyright 老九学堂
 */
public interface CategoryService {

    /**
     * 获取所有类目信息
     * @return
     */
    List<Category> listAll();

    /**
     * 新增商品类目信息
     * @param category
     * @return
     */
    boolean save(Category category);


    Category searchById(int id);

    boolean update(Category category);

    List<Category> listAllParent();
}
