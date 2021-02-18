package com.xuetang9.qingying.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个指明需要授权校验的注解
 *
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/30 10:11
 * @copyright 老九学堂
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
}
