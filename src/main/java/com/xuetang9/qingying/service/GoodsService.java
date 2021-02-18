package com.xuetang9.qingying.service;

import com.xuetang9.qingying.domain.query.GoodsQuery;
import com.xuetang9.qingying.domain.vo.SpuVO;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/20 10:08
 * @copyright 老九学堂
 */
public interface GoodsService {
    List<SpuVO> listByPageAndCondition(int pageNum, int pageSize, GoodsQuery query);
}
