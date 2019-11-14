package com.sparksys.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_product")

public class Goods {
    @Id
    private Integer id;

    /**
     * 蔬菜名称
     */
    private String name;

    private Integer cateId;

    private String proNo;

    private String color;

    private String imageUrl;

    private Date createTime;

    private Date updateTime;

    private Double buyPrize;

    private Double salePrize;
}
