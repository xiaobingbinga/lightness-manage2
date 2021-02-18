package com.xuetang9.qingying.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuetang9.qingying.domain.query.GoodsQuery;
import com.xuetang9.qingying.domain.vo.SpuVO;
import com.xuetang9.qingying.mapper.SpuMapper;
import com.xuetang9.qingying.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/20 10:08
 * @copyright 老九学堂
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,timeout = 5,readOnly = true,rollbackFor = {Exception.class})
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Override
    public List<SpuVO> listByPageAndCondition(int pageNum, int pageSize, GoodsQuery query) {
        Page<SpuVO> page = PageHelper.startPage(pageNum,pageSize);

        spuMapper.selectVOByCondition(query);

        return page;
    }
}
