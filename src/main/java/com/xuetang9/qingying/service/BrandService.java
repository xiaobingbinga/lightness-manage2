package com.xuetang9.qingying.service;

import com.xuetang9.qingying.domain.Brand;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/16 9:41
 * @copyright 老九学堂
 */
public interface BrandService {

    List<Brand> listByPage(int pageNum, int pageSize, Brand condition);

    List<Brand> listByPageAndFilter(int pageNum, int pageSize,String filterWords);

    boolean save(Brand brand);

    List<Brand> listByFilterWords(String filterWords);

    List<Brand> listByCategoryId(int categoryId);
}
