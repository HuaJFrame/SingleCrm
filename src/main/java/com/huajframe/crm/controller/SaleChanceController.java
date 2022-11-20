package com.huajframe.crm.controller;

import com.huajframe.base.BaseController;
import com.huajframe.base.ResultInfo;
import com.huajframe.crm.query.SaleChanceQuery;
import com.huajframe.crm.service.SaleChanceService;
import com.huajframe.crm.service.UserService;
import com.huajframe.crm.utils.LoginUserUtil;
import com.huajframe.crm.vo.SaleChance;
import com.huajframe.crm.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Hua JFarmer
 */
@Controller
@RequestMapping("/sale_chance")
public class SaleChanceController extends BaseController {

    @Autowired
    private SaleChanceService saleChanceService;

    @Autowired
    private UserService userService;

    /**
     * 营销管理主页面转发
     */
    @RequestMapping("/index")
    public String index(){
        return "saleChance/sale_chance";
    }

    /**
     * 营销管理数据查询
     * @param saleChanceQuery
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery){
        return saleChanceService.queryByParamsForTable(saleChanceQuery);
    }

    /**
     * 机会数据添加与更新表单页面视图转发
     * @param id  机会数据修改时传入id
     * @param model 用来存储机会数据修改时回显的数据
     * @return 添加或修改的视图
     */
    @RequestMapping("/addOrUpdateSaleChancePage")
    public String addOrUpdateSaleChancePage(Integer id, Model model){
        if(null !=id){
            model.addAttribute("saleChance",saleChanceService.selectByPrimaryKey(id));
        }
        return "saleChance/add_update";
    }

    /**
     * 添加机会数据
     * @param saleChance 机会数据
     * @param request 用来获取cookies中的用户id
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public ResultInfo saveSaleChance(SaleChance saleChance, HttpServletRequest request){
        //获取到当前登录用户的id，为saleChance设置创建人
        int id = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(id);
        saleChance.setCreateMan(user.getTrueName());
        saleChanceService.saveSaleChance(saleChance);
        return success("机会数据添加成功");
    }

    /**
     * 更新营销机会数据
     * @param saleChance
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateSaleChance(SaleChance saleChance){
        saleChanceService.updateSaleChance(saleChance);
        return success("机会数据更新成功");
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer[] ids){
        saleChanceService.deleteSaleChanceByIds(ids);
        return success("机会数据删除成功");
    }
}
