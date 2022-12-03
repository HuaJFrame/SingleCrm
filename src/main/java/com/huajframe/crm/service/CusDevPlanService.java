package com.huajframe.crm.service;

import com.huajframe.base.BaseService;
import com.huajframe.crm.dao.SaleChanceMapper;
import com.huajframe.crm.utils.AssertUtil;
import com.huajframe.crm.vo.CusDevPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 客户开发机会serive层
 * @author Hua JFarmer
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan, Integer> {

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    @Autowired
    private CusDevPlanService cusDevPlanService;

    /**
     * 添加客户开发计划
     * @param cusDevPlan
     */
    public void saveCusDevPlan(CusDevPlan cusDevPlan){
        checkParam(cusDevPlan);
        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setIsValid(1);
        cusDevPlan.setUpdateDate(new Date());
        AssertUtil.isTrue(insertSelective(cusDevPlan) < 1, "计划项记录添加失败！");
    }

    /**
     * 检查计划项的参数是否合法
     * @param cusDevPlan 计划项
     */
    private void checkParam(CusDevPlan cusDevPlan) {
        Integer saleChanceId = cusDevPlan.getSaleChanceId();
        //判断营销机会是否存在
        AssertUtil.isTrue(saleChanceId == null
                && saleChanceMapper.selectByPrimaryKey(saleChanceId) == null,
                "请设置营销机会id");
        //判断输入的计划项是否为空
        AssertUtil.isTrue(cusDevPlan.getPlanItem() == null, "请输入计划项内容！");
        //判断计划执行日期是否为空
        AssertUtil.isTrue(cusDevPlan.getPlanDate() == null, "请指定计划项日期！");
    }

    /**
     * 删除计划项
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void delCusDevPlan(Integer id){
        CusDevPlan cusDevPlan = cusDevPlanService.selectByPrimaryKey(id);
        AssertUtil.isTrue(id == null ||
                null == cusDevPlan, "待删除记录不存在！");
        cusDevPlan.setIsValid(0);
        AssertUtil.isTrue(updateByPrimaryKeySelective(cusDevPlan) < 1,
                "计划项记录删除失败！");
    }

    /**
     * 开发计划更新
     * @param cusDevPlan
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateCusDevPlan(CusDevPlan cusDevPlan){
        //判断数据库中是否存在待更新记录
        AssertUtil.isTrue(cusDevPlan.getId() == null, "待更新记录不存在！");
        CusDevPlan temp = selectByPrimaryKey(cusDevPlan.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在！");

        checkParam(cusDevPlan);

        AssertUtil.isTrue(updateByPrimaryKeySelective(cusDevPlan) < 1,
                "计划项记录更新失败!");
    }
}
