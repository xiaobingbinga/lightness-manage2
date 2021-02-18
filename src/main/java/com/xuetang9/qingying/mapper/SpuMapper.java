package com.xuetang9.qingying.mapper;

import com.xuetang9.qingying.domain.Spu;
import com.xuetang9.qingying.domain.query.GoodsQuery;
import com.xuetang9.qingying.domain.vo.SpuVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/20 10:10
 * @copyright 老九学堂
 */
public interface SpuMapper extends Mapper<Spu> {

    List<SpuVO> selectVOByCondition(GoodsQuery query);

}
