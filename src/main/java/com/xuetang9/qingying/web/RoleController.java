package com.xuetang9.qingying.web;

import com.github.pagehelper.PageInfo;
import com.xuetang9.qingying.domain.Role;
import com.xuetang9.qingying.domain.RoleResource;
import com.xuetang9.qingying.domain.UserRole;
import com.xuetang9.qingying.domain.dto.RoleResourceDTOS;
import com.xuetang9.qingying.service.RoleService;
import com.xuetang9.qingying.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 20:56
 * @copyright 老九学堂
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public JsonResult queryByFilterWords(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            String filterWords) {
        JsonResult jsonResult = new JsonResult();

        List<Role> list = roleService.listByFilterWords(pageNum,pageSize,filterWords);

        PageInfo<Role> pageInfo = new PageInfo<>(list);

        jsonResult.setCode(200);
        jsonResult.setData(pageInfo);

        return jsonResult;
    }

    @GetMapping("/rolesList")
    public JsonResult queryAll() {
        JsonResult jsonResult = new JsonResult();

        List<Role> list = roleService.listAll();

        jsonResult.setCode(200);
        jsonResult.setData(list);

        return jsonResult;
    }

//    @PutMapping("/roles/{roleId}/permission")
//    public JsonResult updateRoleResource(@PathVariable int roleId, int[] addResourceIds, int[] deleteResourceIds){
//        log.info("添加的资源编号：" + addResourceIds);
//        log.info("删除的资源编号：" + deleteResourceIds);
//        JsonResult jsonResult = new JsonResult();
//        roleService.updateRoleResource(roleId,addResourceIds,deleteResourceIds);
//        return jsonResult;
//    }

    @PutMapping("/roles/{roleId}/permission")
    public JsonResult updateRoleResource(@PathVariable int roleId,@RequestBody RoleResourceDTOS dtos){
        log.info("修改的资源编号：" + dtos);
        JsonResult jsonResult = new JsonResult();
        try {
            roleService.updateRoleResource(roleId,dtos);
            jsonResult.setCode(200);
        } catch (Exception e) {
            jsonResult.setCode(3000);
            e.printStackTrace();
        }
        jsonResult.setAutoShowMessage(true);
        return jsonResult;
    }

    @GetMapping("/users/{userId}/roles")
    public JsonResult queryRolesByUserId(@PathVariable int userId){
        JsonResult jsonResult = new JsonResult();

        List<UserRole> list = roleService.listRolesByUserId(userId);
        jsonResult.setCode(200);
        jsonResult.setData(list);

        return jsonResult;
    }

    @PostMapping("/roles/add")
    public JsonResult addRole(Role role){
        JsonResult jsonResult = new JsonResult();

        try {
            roleService.add(role);
            jsonResult.setCode(200);
            jsonResult.setMessage("角色添加成功");
        } catch (Exception e) {
            jsonResult.setCode(3001);
            jsonResult.setMessage("角色添加失败");
            e.printStackTrace();
        }
        jsonResult.setAutoShowMessage(true);
        return jsonResult;
    }

}
