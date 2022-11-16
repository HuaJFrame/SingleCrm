package com.huajframe.crm.config;

import com.huajframe.crm.interceptor.ExceptionPreHandleInterceptor;
import com.huajframe.crm.interceptor.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Hua JFarmer
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 全局异常处理 前置处理拦截器,主要是为了判断是返回json还是视图
        registry.addInterceptor(this.exceptionPreHandleInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index", "/","/css/**","/js/**","/images/**","/lib/**");
        // 用户未登录拦截器
        registry.addInterceptor(noLoginInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/user/login","/index", "/","/css/**","/js/**","/images/**","/lib/**");
    }


    @Bean
    public ExceptionPreHandleInterceptor exceptionPreHandleInterceptor(){
        return new ExceptionPreHandleInterceptor();
    }

    @Bean
    public NoLoginInterceptor noLoginInterceptor(){
        return new NoLoginInterceptor();
    }
}
