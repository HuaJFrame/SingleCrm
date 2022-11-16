package com.huajframe.base;

import lombok.Data;

/**
 * 返回结果集
 * @author Hua JFarmer
 */
@Data
public class ResultInfo {
    private Integer code=200;
    private String msg="success";
    private Object result;
}
