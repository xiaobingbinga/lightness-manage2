package com.xuetang9.qingying.web;

import com.xuetang9.qingying.domain.Resource;
import com.xuetang9.qingying.domain.RoleResource;
import com.xuetang9.qingying.service.ResourceService;
import com.xuetang9.qingying.util.JsonResult;
import com.xuetang9.qingying.util.NodeMapper;
import com.xuetang9.qingying.util.TreeNode;
import com.xuetang9.qingying.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 11:24
 * @copyright 老九学堂
 */
@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private NodeMapper<Resource> nodeMapper;

    @GetMapping("/resources")
    public JsonResult queryAll(){
        JsonResult jsonResult = new JsonResult();

        // 获得数据
        List<Resource> list = resourceService.listAll();
        // 创建一个树形节点的转换器
//        NodeMapper<Resource> nodeMapper = new NodeMapper<Resource>() {
//            @Override
//            public TreeNode<Resource> objectMapper(Resource object) {
//                return null;
//            }
//        };
        // 转换树形结构
        List<TreeNode<Resource>> tree = TreeUtils.listToTree(list,nodeMapper);

        jsonResult.setCode(200);
        jsonResult.setData(tree);

        return jsonResult;
    }

    @GetMapping("/roles/{roleId}/resources")
    public JsonResult queryResourceByRoleId(@PathVariable int roleId){
        JsonResult jsonResult = new JsonResult();

        List<RoleResource> list = resourceService.listResourceByRoleId(roleId);
        jsonResult.setCode(200);
        jsonResult.setData(list);

        return jsonResult;
    }

    @GetMapping("/users/{userId}/menus")
    public JsonResult queryMenusByUserId(@PathVariable int userId){
        JsonResult jsonResult = new JsonResult();

        List<Resource> list = resourceService.listResourceByUserId(userId);
        // 转换成树形结构
        List<TreeNode<Resource>> tree = TreeUtils.listToTree(list,nodeMapper);
        jsonResult.setCode(200);
        jsonResult.setData(tree);

        return jsonResult;
    }

    @PostMapping("/users/{userId}/check")
    public JsonResult checkPermission(@PathVariable int userId, String url){
        JsonResult jsonResult = new JsonResult();

        boolean hasPermission = resourceService.checkResourceByUrlAndUserId(url,userId);
        jsonResult.setCode(200);
        jsonResult.setData(hasPermission);

        return jsonResult;
    }

}
