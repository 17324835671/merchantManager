package com.sparksys.common.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.property.TextAlignment;
/** 
 *  @application 
 *  @Copyright 上海危网信息科技有限公司版权所有
 *  @company wwsoft
 *  @author Simon 
 *  @time 2019年1月11日 下午2:59:28 
 *  @version 0.1 
 */
@SuppressWarnings("deprecation")
public class PDFUtools {

    public float top = 785;

    public float totalWidth = 550; // 总宽度

    public float left = 55; // 左边空白宽度

    public float currLeft = 55; // 当前左边距，随着文本的追加而增长

    public float bindLeft = 35;

    public String fontPath = "C:\\simfang.ttf";

    public String fileName = "";

    public String filePath = "C:\\document\\";

    public PdfWriter pdfWriter;

    public PdfDocument pdfDocument;

    public PdfDocumentInfo documentInfo;

    public PdfPage pdfPage;

    public Document document;

    public PdfCanvas pdfCanvas;

    public Canvas canvas;

    public PdfFont font;

    public int pageNum = 0;

    public PageSize pageSize = PageSize.A4;

    public PDFUtools() {
        super();
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getTotalWidth() {
        return totalWidth;
    }

    public void setTotalWidth(float totalWidth) {
        this.totalWidth = totalWidth;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getCurrLeft() {
        return currLeft;
    }

    public void setCurrLeft(float currLeft) {
        this.currLeft = currLeft;
    }

    public float getBindLeft() {
        return bindLeft;
    }

    public void setBindLeft(float bindLeft) {
        this.bindLeft = bindLeft;
    }

    public String getFontPath() {
        return fontPath;
    }

    public void setFontPath(String fontPath) {
        this.fontPath = fontPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PdfWriter getPdfWriter() {
        return pdfWriter;
    }

    public void setPdfWriter(PdfWriter pdfWriter) {
        this.pdfWriter = pdfWriter;
    }

    public PdfDocument getPdfDocument() {
        return pdfDocument;
    }

    public void setPdfDocument(PdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument;
    }

    public PdfDocumentInfo getDocumentInfo() {
        return documentInfo;
    }

    public void setDocumentInfo(PdfDocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }

    public PdfPage getPdfPage() {
        return pdfPage;
    }

    public void setPdfPage(PdfPage pdfPage) {
        this.pdfPage = pdfPage;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public PdfCanvas getPdfCanvas() {
        return pdfCanvas;
    }

    public void setPdfCanvas(PdfCanvas pdfCanvas) {
        this.pdfCanvas = pdfCanvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public PdfFont getFont() {
        return font;
    }

    public void setFont(PdfFont font) {
        this.font = font;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    public void setPageSize(PageSize pageSize) {
        this.pageSize = pageSize;
    }

    public void initPDFdata() {
        try {
        	File file = new File(filePath+fileName);
        	File fileParent = file.getParentFile();
        	if (!fileParent.exists()) {
        		fileParent.mkdirs();
        	}
            // 1、创建流对象
            pdfWriter = new PdfWriter(file);
            // 2、创建文档对象
            pdfDocument = new PdfDocument(pdfWriter);
            document = new Document(pdfDocument);
            pdfDocument.setTagged();
            pdfDocument.getCatalog().setLang(new PdfString("zh-cn"));
            pdfDocument.getCatalog().setViewerPreferences(new PdfViewerPreferences().setDisplayDocTitle(false));
            font = PdfFontFactory.createFont(fontPath, "Identity-H", false);
            documentInfo = pdfDocument.getDocumentInfo();
            addNewPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新建页面
     */
    public void addNewPage() {
        pdfDocument.addNewPage();
        pageNum++;
        pdfPage = pdfDocument.getPage(pageNum);
        pdfCanvas = new PdfCanvas(pdfDocument.getPage(pageNum));
        canvas = new Canvas(pdfCanvas, pdfDocument, pdfPage.getPageSize());
    }

    /**
     * html转pdf
     * 
     * @param html
     * @return
     * @throws IOException @
     */
    public static byte[] html2Pdf(String html) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ConverterProperties props = new ConverterProperties();
        FontProvider fp = new FontProvider(); // 提供解析用的字体
        fp.addStandardPdfFonts(); // 添加标准字体库、无中文
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        fp.addDirectory(classLoader.getResource("font").getPath()); // 自定义字体路径、解决中文,可先用绝对路径测试。
        props.setFontProvider(fp);
        // props.setBaseUri(baseResource); // 设置html资源的相对路径
        HtmlConverter.convertToPdf(html, outputStream, props); // 无法灵活设置页边距等
        byte[] result = outputStream.toByteArray();
        outputStream.close();
        return result;
    }

    /**
     * 页眉
     * 
     * @param data @
     */
    public void addHead(String data) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(data);
        paragraph.setFont(font).setFontSize(20);
        paragraph.setBold();
        paragraph.setMarginTop(60);
        paragraph.setTextAlignment(TextAlignment.CENTER);
        canvas.add(paragraph);
        pdfCanvas.setStrokeColor(Color.BLACK).setLineWidth(0.7f).moveTo(left, top - 40).lineTo(totalWidth, top - 40)
                .stroke();

        pdfCanvas.moveTo(left, top - 43).lineTo(totalWidth, top - 43).stroke();
    }

    /**
     * 页尾
     * 
     * @param data
     * @param marginTop @
     */
    public void addFooter(String data, float top) {
        pdfCanvas.setStrokeColor(Color.BLACK).setLineWidth(1.5f).moveTo(left, top).lineTo(totalWidth, top).stroke();
    }

    /**
     * 整横线
     * 
     * @param lineWidth 线条粗细
     * @param left      （x坐标）
     * @param           top（y坐标）
     */
    public void addFullLine(float lineWidth, float left, float top) {
        PdfCanvas canvas = new PdfCanvas(pdfDocument.getPage(pageNum));
        canvas.setStrokeColor(Color.BLACK).setLineWidth(lineWidth).moveTo(left, top).lineTo(totalWidth, top).stroke();
    }

    /**
     * 装订线
     */
    public void addBindingLine() {
        PdfCanvas canvas = new PdfCanvas(pdfDocument.getPage(pageNum));
        canvas.setStrokeColor(Color.BLACK);
        canvas.setLineDash(3, 3, 10);
        canvas.setLineWidth(0.5f).moveTo(bindLeft, 680).lineTo(bindLeft, 552).stroke();
        addPrintText(537, bindLeft - 6, 13, "装");

        canvas.setLineWidth(0.5f).moveTo(bindLeft, 532).lineTo(bindLeft, 404).stroke();
        addPrintText(389, bindLeft - 6, 13, "订");

        canvas.setLineWidth(0.5f).moveTo(bindLeft, 384).lineTo(bindLeft, 256).stroke();
        addPrintText(241, bindLeft - 6, 13, "线");
        canvas.setLineWidth(0.5f).moveTo(bindLeft, 236).lineTo(bindLeft, 100).stroke();
    }

    /**
     * 指定竖线长度
     * 
     * @param lineWidth 线条粗细
     * @param           left（x坐标）
     * @param           right（y坐标）
     * @param top @
     */
    public void addVerticalLine(float lineWidth, float left, float topStart, float topEnd) {
        PdfCanvas canvas = new PdfCanvas(pdfDocument.getPage(pageNum));
        canvas.setStrokeColor(Color.BLACK);
        canvas.setLineWidth(lineWidth).moveTo(left, topStart).lineTo(left, topEnd).stroke();
    }

    /**
     * 指定横线长度
     * 
     * @param lineWidth 线条粗细
     * @param           left（x坐标）
     * @param           right（y坐标）
     * @param top @
     */
    public void addLine(float lineWidth, float left, float right, float top) {
        PdfCanvas canvas = new PdfCanvas(pdfDocument.getPage(pageNum));
        canvas.setStrokeColor(Color.BLACK).setLineWidth(lineWidth).moveTo(left, top).lineTo(right, top).stroke();
    }

    /**
     * 增加矩形线
     * 
     * @param lineWidth 线条粗细
     * @param           left（左边距）
     * @param           right（右边距）
     * @param           top（上边距）
     * @param           hight（高度）
     */
    public void addRectLine(float lineWidth, float left, float right, float top, float hight) {
        float topStart = top;
        float topEnd = top - hight;
        PdfCanvas canvas = new PdfCanvas(pdfDocument.getPage(pageNum));
        canvas.setStrokeColor(Color.BLACK);
        canvas.setLineWidth(lineWidth);

        canvas.moveTo(left, top).lineTo(right, top).stroke();

        canvas.moveTo(left, topEnd).lineTo(right, topEnd).stroke();

        canvas.setLineWidth(lineWidth).moveTo(left, topStart).lineTo(left, topEnd).stroke();

        canvas.setLineWidth(lineWidth).moveTo(right, topStart).lineTo(right, topEnd).stroke();

    }

    /**
     * 添加一个段落 exemple 比如一段文字或者少于一行的可以使用
     * 
     * @param data
     * @param fontSize
     * @param left
     * @param top
     * @param isBlod
     * @param isCenter
     * @param lineIndent @
     */
    public void addParagraphText(String data, float fontSize, float left, float top, boolean isBlod, boolean isCenter,
            boolean lineIndent) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(data);
        paragraph.setWidth(510);
        paragraph.setFont(font).setFontSize(fontSize);
        paragraph.setMarginTop(top);
        if (isBlod) {
            paragraph.setBold();
        }
        if (left == 0) {
            paragraph.setMarginLeft(45);
        } else {
            paragraph.setMarginLeft(left);
        }
        if (isCenter) {
            paragraph.setTextAlignment(TextAlignment.CENTER);
        }
        if (lineIndent) {
            paragraph.setFirstLineIndent(50);
        }
        canvas.add(paragraph);
    }

    /**
     * 指定位置添加文本内容
     * 
     * @param data
     * @param fontSize
     * @param left
     * @param top
     * @param isBlod
     * @param isCenter
     * @param lineIndent @
     */
    public void addFixedPositionText(String data, float fontSize, float left, float top, boolean isBlod,
            boolean isCenter, boolean lineIndent) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(data);
        paragraph.setWidth(500);
        paragraph.setFont(font).setFontSize(fontSize);
        paragraph.setFixedPosition(left, top, 500);
        if (isBlod) {
            paragraph.setBold();
        }
        if (left == 0) {
            paragraph.setMarginLeft(45);
        } else {
            paragraph.setMarginLeft(left);
        }
        if (isCenter) {
            paragraph.setTextAlignment(TextAlignment.CENTER);
        }
        if (lineIndent) {
            paragraph.setFirstLineIndent(50);
        }
        canvas.add(paragraph);
    }

    /**
     * 可控文本位置填充（适合短文本）
     * 
     * @param top      上边距（y坐标）
     * @param          left左边距（x坐标）
     * @param width
     * @param fontSize
     * @param str @
     */
    public void addPrintText(float top, float left, float fontSize, Object str) {
        pdfCanvas.beginText().setFontAndSize(font, fontSize).moveText(left, top).showText(String.valueOf(str))
                .endText();
    }

    /**
     * 垂直文本添加
     * 
     * @param top
     * @param left
     * @param fontSize
     * @param str
     */
    public void addverticalText(float top, float left, float fontSize, String str) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(str);
        paragraph.setWidth(15);
        paragraph.setFont(font).setFontSize(fontSize);
        paragraph.setFixedPosition(left, top, 15);
        canvas.add(paragraph);
        canvas.flush();
    }

    /**
     * 添加图片
     * 
     * @param left
     * @param top
     * @param width
     * @param imagePath
     * @throws MalformedURLException
     */
    public void addImage(float left, float top, float width, String imagePath) throws MalformedURLException {
        Image fox = new Image(ImageDataFactory.create(imagePath));
        fox.setFixedPosition(left, top, width);
        document.add(fox);
    }
}
