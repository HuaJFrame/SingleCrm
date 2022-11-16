package com.huajframe.crm.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义参数异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ParamsException extends RuntimeException {
    private Integer code = 300;
    private String msg = "参数异常!";


    public ParamsException() {
        super("参数异常!");
    }

    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ParamsException(Integer code) {
        super("参数异常!");
        this.code = code;
    }

    public ParamsException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
