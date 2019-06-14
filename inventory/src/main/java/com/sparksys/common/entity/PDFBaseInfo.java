package com.sparksys.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author AMGuo
 * @Description
 * @date 2018/06/14 11:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PDFBaseInfo {
    /**
     * 标题
     */
    private String title;

    /**
     * 表格数据
     */
    private List<PDFTable> tableList;

    /**
     * 总计
     */
    private String sum;

    /**
     * 水印
     */
    private String watermark;
}
