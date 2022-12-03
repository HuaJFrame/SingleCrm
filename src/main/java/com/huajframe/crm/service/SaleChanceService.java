package com.huajframe.crm.service;

import com.huajframe.base.BaseService;
import com.huajframe.crm.enums.DevResult;
import com.huajframe.crm.enums.StateStatus;
import com.huajframe.crm.utils.AssertUtil;
import com.huajframe.crm.utils.PhoneUtil;
import com.huajframe.crm.vo.SaleChance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author Hua JFarmer
 */
@Service
public class SaleChanceService extends BaseService<SaleChance, Integer> {


    /**
     * 添加机会信息
     * @param saleChance 前端传入实体
     */
    public void saveSaleChance(SaleChance saleChance){
        //检查添加机会的基本信息是否正确
        checkParam(saleChance);
        //分配状态默认为未分配
        saleChance.setState(StateStatus.UNSTATE.getType());
        //开发状态为未开发
        saleChance.setDevResult(DevResult.UNDEV.getStatus());
        if(!StringUtils.isEmpty(saleChance.getAssignMan())){
            //如果分配人不为空
            // 已分配
            saleChance.setState(StateStatus.STATED.getType());
            // 开发中
            saleChance.setDevResult(DevResult.DEVING.getStatus());
            //分配时间为现在
            saleChance.setAssignTime(new Date());
        }
        //默认为有效
        saleChance.setIsValid(1);
        //创建和修改时间默认为现在时间
        Date date = new Date();
        saleChance.setCreateDate(date);
        saleChance.setUpdateDate(date);
        //执行插入sql
        AssertUtil.isTrue(insertSelective(saleChance) < 1, "机会数据添加失败!");
    }

    /**
     * 检查添加机会的基本信息是否正确
     * @param saleChance 机会数据实体
     */
    private void checkParam(SaleChance saleChance) {
        AssertUtil.isTrue(StringUtils.isEmpty(saleChance.getCustomerName()),
                "请输入客户名！");
        AssertUtil.isTrue(StringUtils.isEmpty(saleChance.getLinkMan()),
                "请输入联系人！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(saleChance.getLinkPhone()),
                "手机号码格式不正确");
    }

    /**
     * 修改营销机会信息
     * @param saleChance 前端传入实体
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleChance(SaleChance saleChance){
        //传入参数没有
        AssertUtil.isTrue( null == saleChance.getId(),"待更新记录不存在!");
        //数据库没有传入的id参数记录
        SaleChance temp = selectByPrimaryKey(saleChance.getId());
        AssertUtil.isTrue( null == temp,"待更新记录不存在!");
        checkParam(saleChance);
        saleChance.setUpdateDate(new Date());
        if(StringUtils.isEmpty(temp.getAssignMan()) &&
                !StringUtils.isEmpty(saleChance.getAssignMan())){
            //之前未分配，现在已分配
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setAssignTime(new Date());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
        }else if(!StringUtils.isEmpty(temp.getAssignMan()) &&
                StringUtils.isEmpty(saleChance.getAssignMan())) {
            //之前已经分配，现在没有分配了
            saleChance.setState(StateStatus.UNSTATE.getType());
            saleChance.setAssignTime(null);
            saleChance.setDevResult(DevResult.UNDEV.getStatus());
            saleChance.setAssignMan("");
        }else if(!temp.getAssignMan().equals(saleChance.getAssignMan())){
            //两次分配人不同
            saleChance.setAssignTime(new Date());
        }else{
            //其余情况等于等于以前分配时间
            //以前没分配现在也没有，或以前分配过现在没变化
            saleChance.setAssignTime(temp.getAssignTime());
        }
        AssertUtil.isTrue(updateByPrimaryKeySelective(saleChance) < 1,
                "机会数据更新失败!");
    }

    /**
     * 删除机会数据
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleChanceByIds(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0, "请选择待删除的机会数据!");
        AssertUtil.isTrue(deleteBatch(ids) < ids.length, "机会数据删除失败");
    }

    /**
     * 更新营销机会的开发状态
     * @param id 营销机会id
     * @param devResult 开发状态
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleChanceDevResult(Integer id, Integer devResult){
        AssertUtil.isTrue(null == id, "待更新记录不存在！");
        SaleChance saleChance = selectByPrimaryKey(id);
        AssertUtil.isTrue(null == saleChance, "待更新记录不存在！");
        saleChance.setDevResult(devResult);
        AssertUtil.isTrue(updateByPrimaryKeySelective(saleChance) < 1,
                "机会数据更新失败");
    }
}
