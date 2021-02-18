package com.xuetang9.qingying.web;

import com.github.pagehelper.PageInfo;
import com.xuetang9.qingying.domain.User;
import com.xuetang9.qingying.domain.query.LoginQuery;
import com.xuetang9.qingying.interceptor.annotation.Authorization;
import com.xuetang9.qingying.service.UserService;
import com.xuetang9.qingying.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/14 17:03
 * @copyright 老九学堂
 */
//@CrossOrigin
@RestController
@RequestMapping("/api")
@Slf4j
@Authorization
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public Object login(LoginQuery query, HttpSession session) {
//        System.out.println("query:" + query);
//        JsonResult<User> jsonResult = new JsonResult<>();
//        User loginUser = userService.login(query);
//        if (loginUser == null) {
//            jsonResult.setCode(1000);
//            jsonResult.setMessage("账号或密码错误");
//        } else {
//            // 保存登录的信息到session中
//            session.setAttribute("LOGIN_TICKET",loginUser);
//
//            jsonResult.setCode(200);
//            jsonResult.setData(loginUser);
//            jsonResult.setToken(loginUser.getId().toString());
//        }
//        return jsonResult;
//    }

    @GetMapping("/users")
    public JsonResult queryByFilterWords(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            String filterWords) {
        JsonResult jsonResult = new JsonResult();

        List<User> list = userService.listByFilterWords(pageNum, pageSize, filterWords);

        PageInfo<User> pageInfo = new PageInfo<>(list);

        jsonResult.setCode(200);
        jsonResult.setData(pageInfo);

        return jsonResult;
    }

    @PutMapping("/users/{userId}/permission")
    public JsonResult updateRoleResource(@PathVariable int userId,
                                         int[] addRoleIds,
                                         int[] deleteRoleIds) {
        log.info("添加的角色编号：" + addRoleIds);
        log.info("删除的角色编号：" + deleteRoleIds);
        JsonResult jsonResult = new JsonResult();

        try {
            userService.updateUserRole(userId, addRoleIds, deleteRoleIds);
            jsonResult.setCode(200);
            jsonResult.setMessage("账户角色授权成功");
        } catch (Exception e) {
            jsonResult.setCode(4000);
            jsonResult.setMessage("账户角色授权失败");
            e.printStackTrace();
        }

        jsonResult.setAutoShowMessage(true);

        return jsonResult;
    }

    @GetMapping("/users/token/{token}")
    public JsonResult queryByToken(@PathVariable("token") String token){
        JsonResult jsonResult = new JsonResult();
        User user = userService.get(Integer.parseInt(token));
        jsonResult.setCode(200);
        jsonResult.setData(user);
        return jsonResult;
    }

}
