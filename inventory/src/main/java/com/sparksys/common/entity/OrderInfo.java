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
     * 商品id
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品数量
     */
    private Double amount;

    /**
     * 商品价格
     */
    private Double salePrice;



    /**
     * 订单时间
     */
    private Date createTime;

    /**
     * 订单时间
     */
    private Date updateTime;
}
