package com.xuetang9.qingying.web;

import com.github.pagehelper.PageInfo;
import com.xuetang9.qingying.domain.query.GoodsQuery;
import com.xuetang9.qingying.domain.vo.SpuVO;
import com.xuetang9.qingying.service.GoodsService;
import com.xuetang9.qingying.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/20 10:01
 * @copyright 老九学堂
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/list")
    public JsonResult queryByPageAndCondition(@RequestParam(defaultValue = "1") int pageNum,
                                              @RequestParam(defaultValue = "5") int pageSize,
                                              GoodsQuery query){
        JsonResult jsonResult = new JsonResult();
        List<SpuVO> spuVOS = goodsService.listByPageAndCondition(pageNum,pageSize,query);

        PageInfo<SpuVO> pageInfo = new PageInfo<>(spuVOS);

        jsonResult.setCode(200);
        jsonResult.setData(pageInfo);

        return jsonResult;
    }

}
