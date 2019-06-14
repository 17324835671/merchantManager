package com.sparksys.inventory.pdftemplate.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.sparksys.common.dao.PdfTemplateDao;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.PdfTemplate;
import com.sparksys.common.utils.SnowFlakeId;
import com.sparksys.inventory.pdftemplate.service.IPdfTemplateService;
@Service
public class PdfTemplateServiceImpl implements IPdfTemplateService {

	@Resource
	private PdfTemplateDao pdftemplatedao;
	@Override
	public void deletePdfTemplate(PdfTemplate template) {
		if(template.getTemplateid()!=null&&!"".equals(template.getTemplateid())) {
			pdftemplatedao.deletePdfTemplateById(template.getTemplateid());
		}
	}

	@Override
	public void insertPdfTemplate(PdfTemplate template) {
		if(template.getTemplateid()!=null&&!"".equals(template.getTemplateid())) {
			updatePdfTemplate(template);
		}else {
			template.setTemplateid(SnowFlakeId.getSnowFlakeId());
			pdftemplatedao.insertPdfTemplate(template);
		}
	}

	@Override
	public PdfTemplate findPdfTemplateById(Long templateid) {
		PdfTemplate template = pdftemplatedao.findPdfTemplateById(templateid);
		return template;
	}

	@Override
	public void updatePdfTemplate(PdfTemplate template) {
		if(template.getTemplateid()!=null&&!"".equals(template.getTemplateid())) {
			pdftemplatedao.updatePdfTemplateById(template);
		}
	}

	@Override
	public PageBean<PdfTemplate> findPdfTemplateList(int currentPage, int pageSize, Map<String, Object> map) {
		PageHelper.startPage(currentPage, pageSize);
		List<PdfTemplate> list = pdftemplatedao.findPdfTemplateList(map);
		int totalCount = pdftemplatedao.findPdfTemplateListCount(map);
		PageBean<PdfTemplate> pageData = new PageBean<>(currentPage, pageSize, totalCount);
		pageData.setItems(list);
		return pageData;
	}

}
