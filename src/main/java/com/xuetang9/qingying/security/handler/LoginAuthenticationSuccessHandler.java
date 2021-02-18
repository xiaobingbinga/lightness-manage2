package com.xuetang9.qingying.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuetang9.qingying.security.domain.SecurityUser;
import com.xuetang9.qingying.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录授权成功后的处理
 *
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/31 10:18
 * @copyright 老九学堂
 */
@Component
@Slf4j
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("<---------------------->登录授权成功");
        log.debug(authentication.toString());
        // 得到用户的详细信息
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        // 返回JSON对象
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(200);
        jsonResult.setData(securityUser.getUserInfo());
        jsonResult.setToken(securityUser.getUserInfo().getId().toString());
        // 响应JSON字符串到客户端
        response.getWriter().write(new ObjectMapper().writeValueAsString(jsonResult));
    }
}
