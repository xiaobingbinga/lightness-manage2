package com.xuetang9.qingying.mapper;

import com.xuetang9.qingying.domain.RoleResource;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/22 8:58
 * @copyright 老九学堂
 */
public interface RoleResourceMapper extends Mapper<RoleResource> {
    List<RoleResource> selectByUrl(String url);
}
