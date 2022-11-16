package com.huajframe.crm.utils;

import com.huajframe.crm.exceptions.ParamsException;

/**
 * 抛出异常工具类
 */
public class AssertUtil {

    /**
     * 返回前端异常工具类
     * @param flag
     * @param msg
     */
    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamsException(msg);
        }
    }

}
