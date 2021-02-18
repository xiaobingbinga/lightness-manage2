package com.xuetang9.qingying.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuetang9.qingying.util.JsonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/31 16:36
 * @copyright 老九学堂
 */
@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(403);
        jsonResult.setMessage(e.getMessage());

        response.setContentType("application/json;charset=utf-8");

        // 响应json字符串到客户端
        response.getWriter().write(new ObjectMapper().writeValueAsString(jsonResult));
    }
}
