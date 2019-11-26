package com.sparksys.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.List;

@Data
public class Stock {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产品id
     */
    private Integer proId;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 库存
     */
    private Integer stock;


    /**
     * 订单时间
     */
    private Date createTime;

    /**
     * 订单时间
     */
    private Date updateTime;


}
