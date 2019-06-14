package com.sparksys.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;

@Data
@Table(name = "t_vegetables")

public class Vegetables {
    @Id
    private Integer id;

    /**
     * 蔬菜名称
     */
    private String name;

    /**
     * 蔬菜价格
     */
    private Double prize=0.0;
}
