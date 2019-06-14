package com.sparksys.inventory.pdftemplate.service;

import java.util.Map;

import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.PdfTemplate;

public interface IPdfTemplateService {

	void deletePdfTemplate(PdfTemplate template);

	void insertPdfTemplate(PdfTemplate template);

    PdfTemplate findPdfTemplateById(Long templateid);

    void updatePdfTemplate(PdfTemplate template);
    
    PageBean<PdfTemplate> findPdfTemplateList(int currentPage,int pageSize,Map<String, Object> map);
    
}
