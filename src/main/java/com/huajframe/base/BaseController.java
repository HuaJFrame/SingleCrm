package com.huajframe.base;


import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础controller
 * @author Hua JFarmer
 */
public class BaseController {

    /**
     * ModelAttribute注解在方法上,ModelAttribute注解的方法会在Controller每个方法执行前被调用
     * https://blog.csdn.net/yue_xx/article/details/105740360
     * @param request
     */
    @ModelAttribute
    public void preHandler(HttpServletRequest request){
        request.setAttribute("path", request.getContextPath());
    }

    /**
     * 请求成功结果集(无参)
     * @return
     */
    public ResultInfo success(){
        return new ResultInfo();
    }

    /**
     * 请求成功结果集(消息)
     * @return
     */
    public ResultInfo success(String msg){
        ResultInfo resultInfo= new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    /**
     * 请求成功结果集(消息, 结果)
     * @return
     */
    public ResultInfo success(String msg,Object result){
        ResultInfo resultInfo= new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

}
