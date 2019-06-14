package com.sparksys.common.entity;

import lombok.Data;

/**
 * @author AMGuo
 * @Description
 * @date 2018/06/14 11:13
 */
@Data
public class PDFColumn {

    private String text;

    public boolean isBold = false;

    /**
     * 是否换行
     */
    private Boolean isRow;
}
