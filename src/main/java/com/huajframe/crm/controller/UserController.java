package com.huajframe.crm.controller;

import com.huajframe.base.BaseController;
import com.huajframe.base.ResultInfo;
import com.huajframe.crm.model.UserModel;
import com.huajframe.crm.service.UserService;
import com.huajframe.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd){
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = userService.login(userName, userPwd);
        resultInfo.setResult(userModel);
        return success("用户登录成功",userModel);
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request, String oldPassword,
                                     String newPassword, String confirmPassword){
        userService.updateUserPassword(LoginUserUtil.releaseUserIdFromCookie(request), 	oldPassword, newPassword, confirmPassword);
        return success("密码更新成功");
    }

    /**
     * 跳转到修改密码页
     * @return
     */
    @RequestMapping("/toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }

    /**
     * 查询所有的销售人员
     * @return
     */
    @GetMapping("/queryAllSales")
    @ResponseBody
    public List<Map<String, Object>> queryAllSales(){
        return userService.queryAllSales();
    }
}
