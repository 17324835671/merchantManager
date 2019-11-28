package com.sparksys.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Shop {
    @Id
    private Integer id;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 商家地址
     */
    private String address;

    private String mobile;

    private String card;

    private Double totalPrice;


    private Date createTime;

    private Date updateTime;
}
