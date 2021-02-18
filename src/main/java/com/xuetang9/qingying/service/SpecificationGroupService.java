package com.xuetang9.qingying.service;

import com.xuetang9.qingying.domain.vo.SpecGroupVO;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/17 11:56
 * @copyright 老九学堂
 */
public interface SpecificationGroupService {

    List<SpecGroupVO> listAllSpecGroupByCategoryId(int categoryId);

}
