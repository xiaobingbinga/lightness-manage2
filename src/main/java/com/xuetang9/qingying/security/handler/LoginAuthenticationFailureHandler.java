package com.xuetang9.qingying.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuetang9.qingying.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/31 10:30
 * @copyright 老九学堂
 */
@Component
@Slf4j
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        // 构建JsonResult
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(401);
        jsonResult.setMessage(e.getMessage());
        // 响应数据给客户端
        response.getWriter().write(new ObjectMapper().writeValueAsString(jsonResult));
    }
}
