package com.huajframe.crm.query;

import com.huajframe.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 营销机会多条件查询
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SaleChanceQuery extends BaseQuery {
    //客户名称
    private String customerName;

    //创建人
    private String createMan;

    //分配状态
    private Integer state;

    //分配人
    private Integer assignMan;

    //开发状态
    private Integer devResult;
}
