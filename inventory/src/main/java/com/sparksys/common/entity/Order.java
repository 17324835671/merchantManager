package com.sparksys.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商家id
     */
    private Integer shopId;


    /**
     * 商家名称
     */
    private List<OrderInfo> orderInfo;

    /**
     * 商家名称
     */
    private String shopName;


    /**
     * 菜品备注
     */
    private String remark;

    /**
     * 订单创建时间
     */
    private String timeDesc;

    /**
     * 订单时间
     */
    private Date createTime;
}
