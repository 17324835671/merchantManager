package com.sparksys.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;

@Data

public class Buy {

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
}
