package com.sparksys.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sparksys.common.entity.PdfTemplate;
@Repository
public interface PdfTemplateDao {
	
    int deletePdfTemplateById(Long templateid);

    int insertPdfTemplate(PdfTemplate template);

    PdfTemplate findPdfTemplateById(Long templateid);

    int updatePdfTemplateById(PdfTemplate template);
    
    List<PdfTemplate> findPdfTemplateList(Map<String, Object> map);
    
    int findPdfTemplateListCount(Map<String, Object> map);
    
}