package com.xuetang9.qingying.web;

import com.xuetang9.qingying.domain.User;
import com.xuetang9.qingying.domain.query.LoginQuery;
import com.xuetang9.qingying.service.UserService;
import com.xuetang9.qingying.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/30 10:26
 * @copyright 老九学堂
 */
@Controller
@ResponseBody
@RequestMapping("/api")
public class AppController {
    @Autowired
    private UserService userService;

//    @PostMapping("/login")
    public Object login(LoginQuery query, HttpSession session) {
        System.out.println("query:" + query);
        JsonResult<User> jsonResult = new JsonResult<>();
        User loginUser = userService.login(query);
        if (loginUser == null) {
            jsonResult.setCode(1000);
            jsonResult.setMessage("账号或密码错误");
        } else {
            // 保存登录的信息到session中
            session.setAttribute("LOGIN_TICKET",loginUser);

            jsonResult.setCode(200);
            jsonResult.setData(loginUser);
            jsonResult.setToken(loginUser.getId().toString());
        }
        return jsonResult;
    }
}
