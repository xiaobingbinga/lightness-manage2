package com.xuetang9.qingying.mapper;

import com.xuetang9.qingying.domain.Brand;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/16 9:41
 * @copyright 老九学堂
 */
public interface BrandMapper extends Mapper<Brand> {
    List<Brand> selectByCategoryId(int categoryId);
}
