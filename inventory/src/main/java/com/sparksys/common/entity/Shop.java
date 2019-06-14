package com.sparksys.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

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
}
