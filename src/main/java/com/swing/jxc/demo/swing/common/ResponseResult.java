package com.swing.jxc.demo.swing.common;

import lombok.Setter;
import lombok.Getter;

/**
 * @description: 返回信息
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:10
 */
@Setter
@Getter
public class ResponseResult<T> {
    public static final String SYS_ERROR_TEXT = "系统出现异常，请联系管理员";

    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

    private T result;

    public static ResponseResult error() {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.defaultFailCode.getCode());
        result.setMsg(ResultCode.defaultFailCode.getMessage());
        return result;
    }

    public static ResponseResult error(String code, String msg) {
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static ResponseResult success() {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.defaultSuccessCode.getCode());
        result.setMsg(ResultCode.defaultSuccessCode.getMessage());
        return result;
    }

    public static ResponseResult success(String code, String msg) {
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static ResponseResult success(String code, String msg,Object object) {
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setResult(object);
        return result;
    }
}
