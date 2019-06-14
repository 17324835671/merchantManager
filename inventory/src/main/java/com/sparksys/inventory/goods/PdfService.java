package com.sparksys.inventory.goods;

import com.sparksys.common.dao.BuyDao;
import com.sparksys.common.entity.*;
import com.sparksys.common.utils.CheckUtil;
import com.sparksys.common.utils.ExportPdfUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PdfService {

    @Resource
    BuyDao buyDao;

    public void exportPDF(String timeDesc, HttpServletResponse response) throws Exception {
        String fileName="菜单列表";
        Map map=new HashMap();
        map.put("timeDesc",timeDesc);
        List <Buy> orders=buyDao.findByName(map);
        PDFBaseInfo pdfBaseInfo = new PDFBaseInfo();
        pdfBaseInfo = initPDFIncome( orders, 3);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/pdf;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf8"), "utf8"));
        byte[] data = ExportPdfUtil.export(fileName, pdfBaseInfo);
        // 上传PDF到文件服务器，并更新结算状态
        if (data != null && data.length > 0) {
            response.getOutputStream().write(data);
        }
    }

    /**
     * 导出数据
     * @param buyList
     * @return
     */
    private PDFBaseInfo initPDFIncome(List<Buy> buyList, Integer length) {

        PDFBaseInfo pdfBaseInfo = new PDFBaseInfo();
        pdfBaseInfo.setTitle("菜单列表");
        pdfBaseInfo.setWatermark(ExportPdfUtil.imagePath);
        List<PDFTable> tableList = new ArrayList<>();
        PDFTable pdfTable = new PDFTable();
        pdfTable.setTitle("菜单列表");
        float[] width = new float[length];
        for (int i = 0; i < length; i++) {
            width[i] = 5f;
        }
        pdfTable.setConWidths(width);
        pdfTable.setWidthPercent(100);
        //表头
        List <String> list=new ArrayList<>();
        //list.add("序号");
        list.add("菜品名称");
        list.add("数量明细");
        list.add("总计");
        List<PDFColumn> columnList = initIncomeHead(list);
        buyList.forEach(incomeVo -> {
            List<String> values = new ArrayList<>();
            try {
                values = reflexEntity(incomeVo);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            int i = 1;
            int j = values.size();
            for (String value : values) {
                PDFColumn column = new PDFColumn();
                column.setText(value.toString());
                column.setBold(false);
                column.setIsRow((i == j) ? true : false);
                i++;
                columnList.add(column);
            }
        });
        pdfTable.setColumns(columnList);
        tableList.add(pdfTable);
        pdfBaseInfo.setTableList(tableList);
        return pdfBaseInfo;
    }

    /**
     * 初始化表头数据
     *
     * @param list
     * @return
     */
    private List<PDFColumn> initIncomeHead(List<String> list) {
        List<PDFColumn> resultList = new ArrayList<>();
        int i = 1;
        int j = list.size();
        for (String s : list) {
            PDFColumn column = new PDFColumn();
            column.setText(s);
            column.setBold(true);
            column.setIsRow((i == j) ? true : false);
            resultList.add(column);
            i++;
        }
        return resultList;
    }


    /**
     * 获取实体类所有属性值
     *
     * @param incomeVo
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private List<String> reflexEntity(Object incomeVo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] field = incomeVo.getClass().getDeclaredFields();
        List<String> values = new ArrayList<>();
        // 遍历所有属性
        for (int j = 0; j < field.length; j++) {
            // 获取属性的名字
            String name = field[j].getName();
            // 将属性的首字符大写，方便构造get，set方法
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            // 获取属性的类型
            String type = field[j].getGenericType().toString();
            // 如果type是类类型，则前面包含"class "，后面跟类名
            Method m = incomeVo.getClass().getMethod("get" + name);
            String value = "";
            // 调用getter方法获取属性值
            if (type.contains("Date")) {
                Date date = (Date) m.invoke(incomeVo);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                value = sdf.format(date);
            } else if (type.contains("Integer")) {
                value = String.valueOf((Integer) m.invoke(incomeVo));
            } else if (type.contains("BigDecimal")) {
                value = String.valueOf((((BigDecimal) m.invoke(incomeVo)).doubleValue()));
            } else {
                value = (String) m.invoke(incomeVo);
            }
                values.add(value);
        }
        return values;
    }


}
