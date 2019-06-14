package com.sparksys.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class OrderInfo {
    @Id
    private Integer id;

    /**
     * 总订单id
     */
    private Integer orderId;

    /**
     * 商家id
     */
    private Integer shopId;


    /**
     * 菜品id
     */
    private String vegetablesId;

    /**
     * 菜品名称
     */
    private String vegetablesName;

    /**
     * 菜品数量
     */
    private Double number;

    /**
     * 菜品单位
     */
    private String unit;

    /**
     * 菜品备注
     */
    private String remark;

    /**
     * 订单时间
     */
    private Date createTime;
}
