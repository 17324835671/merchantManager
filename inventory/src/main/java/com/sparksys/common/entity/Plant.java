package com.sparksys.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Plant {
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


    private Date createTime;

    private Date updateTime;
}
