package com.xuetang9.qingying.service.impl;

import com.xuetang9.qingying.domain.SpecificationGroup;
import com.xuetang9.qingying.domain.SpecificationParam;
import com.xuetang9.qingying.domain.vo.SpecGroupVO;
import com.xuetang9.qingying.domain.vo.SpecParamVO;
import com.xuetang9.qingying.mapper.SpecificationGroupMapper;
import com.xuetang9.qingying.mapper.SpecificationParamMapper;
import com.xuetang9.qingying.service.SpecificationGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/17 11:58
 * @copyright 老九学堂
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = true,timeout = 5,rollbackFor = {Exception.class})
public class SpecificationGroupServiceImpl implements SpecificationGroupService {

    @Autowired
    private SpecificationGroupMapper specificationGroupMapper;

    @Autowired
    private SpecificationParamMapper specificationParamMapper;

    @Override
    public List<SpecGroupVO> listAllSpecGroupByCategoryId(int categoryId) {
        // 构建查询条件
        Example example = new Example(SpecificationGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",categoryId);

        // 根据分类获取所有的分组
        List<SpecificationGroup> groups = specificationGroupMapper.selectByExample(example);

        // 根据分组重新组装页面数据
        List<SpecGroupVO> groupVOS = new ArrayList<>(groups.size());
        for (SpecificationGroup group : groups) {
            SpecGroupVO groupVO = new SpecGroupVO();
            BeanUtils.copyProperties(group,groupVO);
            groupVOS.add(groupVO);

//            Example paramExample = new Example(SpecificationParam.class);
//            Example.Criteria paramCriteria = paramExample.createCriteria();
//            paramCriteria.andEqualTo("specificationGroupId",group.getId());
            SpecificationParam specificationParam = new SpecificationParam();
            specificationParam.setSpecificationGroupId(group.getId());
            // 查询分组下对应的参数
//            List<SpecificationParam> params = specificationParamMapper.selectByExample(paramCriteria);
            List<SpecificationParam> params = specificationParamMapper.select(specificationParam);
            // 组装VO
            List<SpecParamVO> paramVOS = new ArrayList<>(params.size());
            for (SpecificationParam param : params) {
                SpecParamVO paramVO = new SpecParamVO();
                BeanUtils.copyProperties(param,paramVO);
                // 添加
                paramVOS.add(paramVO);
            }
            // 把paramVOS田间道groupVO中
            groupVO.setParams(paramVOS);
        }

        return groupVOS;
    }
}
