package com.huajframe.base;

import lombok.Data;

/**
 * 条件查询
 * @author Hua JFarmer
 */
@Data
public class BaseQuery {
    private Integer page=1;
    private Integer limit=10;
}
