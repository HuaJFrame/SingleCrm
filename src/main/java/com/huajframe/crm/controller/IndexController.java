package com.huajframe.crm.controller;

import com.huajframe.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Hua JFarmer
 */
@Controller
public class IndexController extends BaseController {
    /**
     * 系统登录页
     * @return
     */
    @RequestMapping({"index", "/"})
    public String index(){
        return "index";
    }


    /**
     * 系统界面欢迎页
     * @return
     */
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 后端管理主页面
     * @return
     */
    @RequestMapping("main")
    public String main(){
        return "main";
    }
}
