package com.huajframe.crm.query;

import com.huajframe.base.BaseQuery;
import lombok.Data;

/**
 * 营销机会多条件查询
 * @author Hua JFarmer
 */
@Data
public class SaleChanceQuery extends BaseQuery {
    //客户名称
    private String customerName;

    //创建人
    private String createMan;

    //分配状态
    private Integer state;
}
