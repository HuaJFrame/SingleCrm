package com.huajframe.crm.controller;

import com.huajframe.base.BaseController;
import com.huajframe.base.ResultInfo;
import com.huajframe.crm.query.CusDevPlanQuery;
import com.huajframe.crm.service.CusDevPlanService;
import com.huajframe.crm.service.SaleChanceService;
import com.huajframe.crm.vo.CusDevPlan;
import com.huajframe.crm.vo.SaleChance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/cus_dev_plan")
public class CusDevPlanController extends BaseController {

    @Autowired
    private SaleChanceService saleChanceService;

    @Autowired
    private CusDevPlanService cusDevPlanService;

    @RequestMapping("/index")
    public String index(){
        return "cusDevPlan/cus_dev_plan";
    }

    /**
     * 到计划项查询界面
     * @param sid
     * @param model
     * @return
     */
    @RequestMapping("/toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid, Model model){
        SaleChance saleChance = saleChanceService.selectByPrimaryKey(sid);
        model.addAttribute("saleChance", saleChance);
        return "cusDevPlan/cus_dev_plan_data";
    }

    /**
     * 根据营销机会id进行计划项查询
     * @param cusDevPlanQuery
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery){
        return cusDevPlanService.queryByParamsForTable(cusDevPlanQuery);
    }

    /**
     * 添加计划项
     * @param cusDevPlan 计划项数据
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public ResultInfo saveCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.saveCusDevPlan(cusDevPlan);
        return success("计划项添加成功");
    }

    /**
     * 跳转到添加开发计划窗口
     * @param sid 营销机会id
     * @param id 开发机会id,用来修改数据是回显
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateCusDevPlanPage")
    public String addOrUpdateCusDevPlanPage(Integer sid,Integer id, Model model){
        model.addAttribute("cusDevPlan",cusDevPlanService.selectByPrimaryKey(id));
        model.addAttribute("sid",sid);
        return "cusDevPlan/add_update";
    }

    /**
     * 删除计划项
     * @param id 计划项id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id){
        cusDevPlanService.delCusDevPlan(id);
        return success("计划项删除成功!");
    }

    /**
     * 更新开发计划
     * @param cusDevPlan
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success("计划项更新成功！");
    }

}
