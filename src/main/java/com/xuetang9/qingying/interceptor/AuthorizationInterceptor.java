package com.xuetang9.qingying.interceptor;

import com.xuetang9.qingying.domain.User;
import com.xuetang9.qingying.interceptor.annotation.Authorization;
import com.xuetang9.qingying.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/30 9:28
 * @copyright 老九学堂
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private ResourceService resourceService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("处理请求之后");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断拦截的处理是否是HandlerMethod
        if (!(handler instanceof HandlerMethod)){
            // 直接放行
            return true;
        }
        // 把处理的对象转换为HandlerMethod的类型
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取方法上的注解
        Authorization authorization = getAuthorization(handlerMethod);

        if (authorization == null){
            // 没有注解表示不需要授权校验，直接放行
            return true;
        }

        boolean isJsonResult = checkJsonResult(handlerMethod);

        // 表明需要执行授权校验
        // 1、先校验是否登录
        User user = (User) request.getSession().getAttribute("LOGIN_TICKET");
        String token = null;
        Integer userId = null;
        if (user == null){
            // 校验是否有token
            token = request.getHeader("Authorization");
            // 判断token
            if (token != null){
                userId = Integer.parseInt(token);
            }
        }else{
            userId = user.getId();
        }

        if (userId == null){
            requestDispatcher(isJsonResult,request,response);
            return false;
        }

        // 2、校验是否有权限能够访问
        String resourceUrl = request.getRequestURI();
        System.out.println(resourceUrl);
        boolean valid = resourceService.checkResourceByUrlAndUserId(resourceUrl,userId);
        if (valid){
            return true;
        }
        requestDispatcher(isJsonResult,request,response);
        return false;
    }

    private Authorization getAuthorization(HandlerMethod handlerMethod){
        // 获取方法上的注解
        Authorization authorization = handlerMethod.getMethodAnnotation(Authorization.class);
        // 判断授权注解是否存在
        if (authorization == null){
            // 从方法所在的类上查找授权校验的注解
            authorization = handlerMethod.getBeanType().getAnnotation(Authorization.class);
        }
        return authorization;
    }

    private boolean checkJsonResult(HandlerMethod handlerMethod){
        // 获取Controller执行方法返回的类型：是跳转页面，还是响应JSON数据
        ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
        if (responseBody != null){
            return true;
        }
        // 如果方法上不存在ResponseBody注解，就在Controller的类型上面查找
        responseBody = handlerMethod.getBeanType().getAnnotation(ResponseBody.class);
        if (responseBody != null){
            return true;
        }
        RestController restController = handlerMethod.getBeanType().getAnnotation(RestController.class);
        if (restController != null){
            return true;
        }
        return false;
    }

    private void requestDispatcher(boolean isJsonResult, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isJsonResult){
            request.getRequestDispatcher("/un_authorization_page").forward(request,response);
        }else {
            request.getRequestDispatcher("/un_authorization_json").forward(request,response);
        }
    }
}
