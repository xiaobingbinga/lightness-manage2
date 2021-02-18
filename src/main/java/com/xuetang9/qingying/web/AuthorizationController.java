package com.xuetang9.qingying.web;

import com.xuetang9.qingying.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/30 10:53
 * @copyright 老九学堂
 */
@Controller
public class AuthorizationController {

    @RequestMapping("/un_authorization_page")
    public void un_authorization_page(){
    }

    @RequestMapping("/un_authorization_json")
    @ResponseBody
    public JsonResult un_authorization_json(){
        JsonResult jsonResult = new JsonResult();

        jsonResult.setCode(401);
        jsonResult.setMessage("没有权限");
        jsonResult.setAutoShowMessage(true);

        return jsonResult;
    }

}
