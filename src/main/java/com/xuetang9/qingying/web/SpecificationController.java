package com.xuetang9.qingying.web;

import com.xuetang9.qingying.domain.vo.SpecGroupVO;
import com.xuetang9.qingying.service.SpecificationGroupService;
import com.xuetang9.qingying.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/17 12:11
 * @copyright 老九学堂
 */
@RestController
@RequestMapping("/api")
public class SpecificationController {

    @Autowired
    private SpecificationGroupService specificationService;

    @GetMapping("/category/{id}/specification/groups")
    public JsonResult querySpecGroupsBy(@PathVariable int id){
        JsonResult jsonResult = new JsonResult();
        List<SpecGroupVO> groupVOS = specificationService.listAllSpecGroupByCategoryId(id);

        jsonResult.setCode(200);
        jsonResult.setData(groupVOS);

        return jsonResult;
    }
}
