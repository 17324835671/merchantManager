package com.sparksys.common.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.sparksys.common.entity.PDFBaseInfo;
import com.sparksys.common.entity.PDFColumn;
import com.sparksys.common.entity.PDFTable;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author AMGuo
 * @Description
 * @date 2018/06/14 10:23
 */
public class ExportPdfUtil {

    public static String imagePath = "https://img3.mklimg.com/g2/M00/3C/E1/rBBrCls8dvCAGEWYAAA8WqZuWD4271.png!";

    /**
     * 报表导出功能
     *
     * @param fileName
     * @param pdfBaseInfo
     * @throws Exception
     */
    public static byte[] export(String fileName, PDFBaseInfo pdfBaseInfo) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (!CheckUtil.isEmpty(pdfBaseInfo)) {
            Document document = new Document();
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font docFont = new Font(bfChinese, 11, Font.NORMAL);
            Font subTitle = new Font(bfChinese, 16, Font.NORMAL);
            Font footnoteFont = new Font(bfChinese, 10, Font.NORMAL);


            PdfWriter writer = PdfWriter.getInstance(document, baos);

            if (!CheckUtil.isEmpty(pdfBaseInfo.getTitle())) {
                //设置标题
                document.addTitle(pdfBaseInfo.getTitle());
            }
            //打开文档
            document.open();
            if (!CheckUtil.isEmpty(pdfBaseInfo.getWatermark())) {
                //添加水印

                //通过PDF页面事件模式添加文字水印功能
                writer.setPageEvent(new PictureWaterMarkPdfPageEvent(pdfBaseInfo.getWatermark()));
            }

            if (!CheckUtil.isEmpty(pdfBaseInfo.getTableList())) {
                for (PDFTable table : pdfBaseInfo.getTableList()) {
                    Paragraph title = new Paragraph(table.getTitle(), subTitle);
                    document.add(title);
                    if (table.getTextDescription() != null) {
                        Paragraph textDescription = new Paragraph(table.getTextDescription() + "\n", docFont);
                        textDescription.setSpacingBefore(10f);
                        textDescription.setIndentationLeft(50);
                        document.add(textDescription);
                    }
                    if (table.getFootnote() != null) {
                        Paragraph footnote = new Paragraph(table.getFootnote(), footnoteFont);
                        footnote.setIndentationLeft(50);
                        document.add(footnote);
                    }
                    PdfPTable conTable = new PdfPTable(table.getConWidths());
                    conTable.setSpacingBefore(5f);
                    conTable.setWidthPercentage(CheckUtil.isEmpty(table.getWidthPercent()) ? 100 : table.getWidthPercent());
                    for (PDFColumn column : table.getColumns()) {
                        PdfPCell cell = new PdfPCell(new Paragraph(column.getText(), docFont));
                        if (column.isBold) {
                            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        }
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setSpaceCharRatio(10f);
                        conTable.addCell(cell);
                        if (column.getIsRow()) {
                            conTable.completeRow();
                        }
                    }
                    document.add(conTable);
                }
            }
            if (!CheckUtil.isEmpty(pdfBaseInfo.getSum())) {
                Paragraph sum = new Paragraph(pdfBaseInfo.getSum(), footnoteFont);
                document.add(sum);
            }

            //关闭文档
            document.close();
        }
        return baos.toByteArray();
    }

    /**
     * 添加水印（单页）
     *
     * @throws Exception
     * @author ShaoMin
     */
    public static void addShuyinByWriter(PdfWriter writer, String watermark) throws Exception {


        /*
         * PDF分为四层，第一层和第四层由低级操作来进行操作，第二层、第三层由高级对象操作(从下往上)
         * 第一层操作只能使用PdfWriter.DirectContent操作，第四层使用DirectContentUnder操作,。
         * 第二层和第三层的PdfContentByte是由IText内部操作，没有提供api接口。
         */
        Integer pageSize = Integer.valueOf(writer.getPageSize().toString());
        for (int i = 0; i < pageSize; i++) {
            PdfContentByte under = writer.getDirectContent();
            under.beginText();
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            under.setFontAndSize(bf, 18);
            under.setTextMatrix(30, 30);
            under.setColorFill(BaseColor.LIGHT_GRAY);
            under.showTextAligned(Element.ALIGN_LEFT, watermark, 230, 430, 45);
            under.endText();
        }
    }

    /**
     * 为PDF分页时创建添加文本水印的事件信息
     */
    static class TextWaterMarkPdfPageEvent extends PdfPageEventHelper {

        private String waterMarkText;

        public TextWaterMarkPdfPageEvent(String waterMarkText) {
            this.waterMarkText = waterMarkText;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                //获取pdf内容正文页面宽度
                float pageWidth = document.right() + document.left();
                //获取pdf内容正文页面高度
                float pageHeight = document.top() + document.bottom();
                //设置水印字体格式
                BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                Font waterMarkFont = new Font(base, 20, Font.BOLD, BaseColor.LIGHT_GRAY);
                PdfContentByte waterMarkPdfContent = writer.getDirectContent();
                Phrase phrase = new Phrase(waterMarkText, waterMarkFont);
                ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase,
                        pageWidth * 0.25f, pageHeight * 0.2f, 45);
                ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase,
                        pageWidth * 0.25f, pageHeight * 0.5f, 45);
                ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase,
                        pageWidth * 0.25f, pageHeight * 0.8f, 45);
                ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase,
                        pageWidth * 0.65f, pageHeight * 0.2f, 45);
                ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase,
                        pageWidth * 0.65f, pageHeight * 0.5f, 45);
                ColumnText.showTextAligned(waterMarkPdfContent, Element.ALIGN_CENTER, phrase,
                        pageWidth * 0.65f, pageHeight * 0.8f, 45);
            } catch (DocumentException de) {
                de.printStackTrace();
            } catch (IOException de) {
                de.printStackTrace();
            }
        }
    }

    /**
     * 为PDF分页时创建添加图片水印的事件信息
     */

    public static class PictureWaterMarkPdfPageEvent extends PdfPageEventHelper {

        private String waterMarkFullFilePath;

        private Image waterMarkImage;

        public PictureWaterMarkPdfPageEvent(String waterMarkFullFilePath) {
            this.waterMarkFullFilePath = waterMarkFullFilePath;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                // 获取pdf内容正文页面宽度
                float pageWidth = document.right() + document.left();
                // 获取pdf内容正文页面高度
                float pageHeight = document.top() + document.bottom();
                PdfContentByte waterMarkPdfContent = writer.getDirectContentUnder();
                // 仅设置一个图片实例对象，整个PDF文档只应用一个图片对象，极大减少因为增加图片水印导致PDF文档大小增加
                if (waterMarkImage == null) {
                    waterMarkImage = Image.getInstance(waterMarkFullFilePath);
                }
                // 添加水印图片，文档正文内容采用横向三列，竖向两列模式增加图片水印
                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 1; j++) {
                        waterMarkPdfContent.addImage(getWaterMarkImage(waterMarkImage, 0, 0));
                    }
                }
                PdfGState gs = new PdfGState();
                // 设置透明度为0.2
                gs.setFillOpacity(0.8f);
                gs.setStrokeOpacity(0.8f);
                gs.setOverPrintStroking(true);
                waterMarkPdfContent.setGState(gs);
            } catch (DocumentException de) {
                de.printStackTrace();
                System.err.println("pdf watermark font:" + de.getMessage());
            } catch (IOException de) {
                de.printStackTrace();
                System.err.println("pdf watermark font:" + de.getMessage());
            }
        }
    }

    /**
     * 变更一个图片对象的展示位置和角度信息
     *
     * @param waterMarkImage
     * @param xPosition
     * @param yPosition
     * @return
     */
    public static Image getWaterMarkImage(Image waterMarkImage, float xPosition, float yPosition) {
        // 坐标
        waterMarkImage.setAbsolutePosition(xPosition, yPosition);
        // 依照比例缩放
        waterMarkImage.scalePercent(100);
        return waterMarkImage;
    }
}
