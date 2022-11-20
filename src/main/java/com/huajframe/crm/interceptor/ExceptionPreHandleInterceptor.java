package com.huajframe.crm.interceptor;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 判断Controller是返回json还是页面的拦截器，为统一异常处理返回结果做判断依据
 * @author Hua JFarmer
 */
public class ExceptionPreHandleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            //不是HandlerMethod，允许通过
            return true;
        }

        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();

        /**
         * 返回视图的条件是
         *      1.返回值必须是String类型
         *      2.方法上没有@ResponseBody注解
         *      3.类上没有@RestController注解
         */
        boolean isPage = method.getReturnType().equals(String.class);
        boolean isNotJson = !method.isAnnotationPresent(ResponseBody.class);
        boolean isController = !hm.getBeanType().isAnnotationPresent(RestController.class);

        //controller_response_is_view 这个表示controller的返回响应类型是页面
        request.setAttribute("method_return_is_view", isPage && isNotJson && isController);
        return true;
    }
}
