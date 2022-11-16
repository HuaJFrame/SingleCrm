package com.huajframe.crm.interceptor;

import com.huajframe.crm.exceptions.NoLoginException;
import com.huajframe.crm.service.UserService;
import com.huajframe.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hua JFarmer
 */
public class NoLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 获取cookie  解析用户id
         *    如果用户id存在 并且 数据库存在对应用户记录  放行  否则进行拦截 重定向到登录
         */
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        if (userId == 0 || null == userService.selectByPrimaryKey(userId)) {
            throw new NoLoginException();
        }
        return true;
    }
}
