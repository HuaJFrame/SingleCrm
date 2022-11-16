package com.huajframe.crm.advice;

import com.alibaba.fastjson.JSON;
import com.huajframe.base.ResultInfo;
import com.huajframe.crm.exceptions.NoLoginException;
import com.huajframe.crm.exceptions.ParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Hua JFarmer
 */
@ControllerAdvice
@Slf4j
public class UserControllerAdvice {

    @ExceptionHandler(ParamsException.class)
    public ModelAndView handleException(ParamsException e, HttpServletRequest request, HttpServletResponse response) {
        //获取拦截器判断的返回结果类型
        Object o = request.getAttribute("method_return_is_view");
        if (o == null) {
            log.error("", e);
            throw e;
        }
        //是否是html/text
        boolean isView = (boolean) o;
        if (isView) {
            //返回页面
            ModelAndView mv = new ModelAndView();
            //暂未设置错误页
            mv.setViewName("error");
            mv.addObject("msg", e.getMsg());
            mv.addObject("code", e.getCode());
            return mv;
        } else {
            //返回json
            // ResultInfo resultInfo=new ResultInfo();
            // resultInfo.setCode(e.getCode());
            // resultInfo.setMsg(e.getMsg());
            //
            // mv.setView(new MappingJackson2JsonView());
            // mv.addObject("resultInfo", resultInfo);

            //返回json
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            try (PrintWriter pw = response.getWriter()) {
                pw.write(JSON.toJSONString(resultInfo));
                pw.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    @ExceptionHandler(NoLoginException.class)
    public ModelAndView handleNoLoginException(NoLoginException e, HttpServletRequest request) {
        /**
         * 首先判断异常类型
         *   如果异常类型为未登录异常  执行视图转发
         */
        ModelAndView mv = new ModelAndView();
        mv.setViewName("no_login");
        mv.addObject("msg", e.getMsg());
        mv.addObject("path", request.getContextPath());
        return mv;
    }
}
