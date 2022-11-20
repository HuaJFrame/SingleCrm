package com.huajframe.crm.enums;


/**
 * 分配状态枚举类
 * @author Hua JFarmer
 */
public enum  StateStatus {
    // 未分配
    UNSTATE(0),
    // 已分配
    STATED(1);

    private final Integer type;

    StateStatus(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
