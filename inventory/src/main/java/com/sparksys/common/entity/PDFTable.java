package com.sparksys.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @author AMGuo
 * @Description
 * @date 2018/06/14 11:12
 */
@Data
public class PDFTable {
    /**
     * 表格标题
     */
    private String title;

    /**
     * 表格宽度占比
     */
    private float widthPercent;

    /**
     * 单元格值
     */
    private List<PDFColumn> columns;

    /**
     *
     */
    private float[] conWidths;

    /**
     * 简介
     */
    private String textDescription;

    /**
     * 脚注
     */
    private String footnote;
}
