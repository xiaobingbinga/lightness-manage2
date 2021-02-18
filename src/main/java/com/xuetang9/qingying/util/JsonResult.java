package com.xuetang9.qingying.util;

import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/14 17:05
 * @copyright 老九学堂
 */
public class JsonResult<T> implements Serializable {

    private int code;
    private T data;
    private String message;
    private Object error;
    private String token;
    private Boolean autoShowMessage;

    public Boolean getAutoShowMessage() {
        return autoShowMessage;
    }

    public void setAutoShowMessage(Boolean autoShowMessage) {
        this.autoShowMessage = autoShowMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
