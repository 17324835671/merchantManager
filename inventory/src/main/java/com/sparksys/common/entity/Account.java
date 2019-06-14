package com.sparksys.common.entity;

import lombok.Data;

@Data

public class Account {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单明细
     */
    private String detail;

    /**
     * 菜单总计
     */
    private String count;

    /**
     * 单价
     */
    private Double prize;

    /**
     * 总价
     */
    private Double totalPrize;


}
