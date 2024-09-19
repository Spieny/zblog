package com.ziahh.zblog.common.exception;

/**
 * @Title: BusinessException
 * @Author Ziahh
 * @Package com.ziahh.exception
 * @Date 2024/5/8 下午12:52
 * @description: 业务异常类
 */
public class BusinessException extends RuntimeException {

    private final Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code = -1;
    }

}
