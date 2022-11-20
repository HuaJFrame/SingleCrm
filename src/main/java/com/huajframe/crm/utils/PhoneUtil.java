package com.huajframe.crm.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hua JFarmer
 */
public class PhoneUtil {

    public static  boolean isMobile(String phone){
        Pattern p;
        Matcher m;
        boolean b = false;
        // 验证手机号
        String s2="^[1][3,4,5,7,8][0-9]{9}$";
        if(!StringUtils.isEmpty(phone)){
            p = Pattern.compile(s2);
            m = p.matcher(phone);
            b = m.matches();
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(isMobile("18200001234"));
    }
}
