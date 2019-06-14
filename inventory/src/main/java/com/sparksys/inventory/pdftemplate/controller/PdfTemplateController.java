package com.sparksys.inventory.pdftemplate.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.PdfTemplate;
import com.sparksys.common.entity.SysUser;
import com.sparksys.inventory.pdftemplate.service.IPdfTemplateService;
@Controller
public class PdfTemplateController extends CommonController{

	@Resource
	private IPdfTemplateService pdfTemplateService;
	
	@RequestMapping(value ="/findPdfTemplateList")
	public String findPdfTemplateList(){
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String templatename = this.getRequest().getParameter("templatename");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		if(templatename!=null&&!"".equals(templatename)) {
			map.put("templatename", templatename);
		}
		PageBean<PdfTemplate> page = pdfTemplateService.findPdfTemplateList(pageNum, 10,map);
		this.getRequest().setAttribute("templateList", page.getItems());
		this.getRequest().setAttribute("pageTag", page);
		return "pdftemplate/pdftemplateList";
	}
	@RequestMapping(value ="/pdftemplateSearch",method = RequestMethod.GET)
	public String pdftemplateSearch(){
		return "pdftemplate/pdftemplateSearch";
		
	}
	@RequestMapping(value ="/templateView")
	public String templateView(){
		String templateid= this.getRequest().getParameter("templateid");
		PdfTemplate template = new PdfTemplate();
		template= pdfTemplateService.findPdfTemplateById(Long.valueOf(templateid));
		this.getRequest().setAttribute("template", template);
		return "pdftemplate/pdftemplateView";
		
	}
	@RequestMapping(value ="/pdfView")
	public String pdfView(){
		String templateid= this.getRequest().getParameter("templateid");
		this.getRequest().setAttribute("templateid", templateid);
		return "pdftemplate/pdfView";
	}
	@RequestMapping(value ="/AddTemplateForward")
	public String AddTemplateForward(){
		return "pdftemplate/pdftemplateAdd";
	}
	
	@RequestMapping(value ="/updatePdftemplateForward")
	public String updatePdftemplateForward(){
		String templateid= this.getRequest().getParameter("templateid");
		PdfTemplate template = new PdfTemplate();
		template= pdfTemplateService.findPdfTemplateById(Long.valueOf(templateid));
		this.getRequest().setAttribute("template", template);
		return "pdftemplate/pdftemplateUpdate";
	}
	@RequestMapping(value ="/savePdftemplate")
	@ResponseBody
	public void savePdftemplate(){
		SysUser sessionUserBean = this.getSessionUserBean();
		String templatename= this.getRequest().getParameter("templatename");
		try {
			PdfTemplate template= new PdfTemplate();
			template.setTemplatename(templatename);
			template.setCdPerson(sessionUserBean.getUserId());
			template.setUpPerson(sessionUserBean.getUserId());
			pdfTemplateService.insertPdfTemplate(template);
			this.success("新增PDF模板成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增PDF模板失败",null);
		}
	}
	@RequestMapping(value ="/updatePdftemplate")
	@ResponseBody
	public void updatePdftemplate(){
		SysUser sessionUserBean = this.getSessionUserBean();
		String templateid= this.getRequest().getParameter("templateid");
		String templatename= this.getRequest().getParameter("templatename");
		try {
			PdfTemplate template= new PdfTemplate();
			template.setTemplateid(Long.valueOf(templateid));
			template.setTemplatename(templatename);
			template.setUpPerson(sessionUserBean.getUserId());
			pdfTemplateService.updatePdfTemplate(template);
			this.success("修改PDF模板成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改PDF模板失败",null);
		}
	}
	@RequestMapping(value ="/deletePdftemplate")
	@ResponseBody
	public void deletePdftemplate(){
		String templateid= this.getRequest().getParameter("templateid");
		try {
			PdfTemplate template= new PdfTemplate();
			template.setTemplateid(Long.valueOf(templateid));
			pdfTemplateService.deletePdfTemplate(template);
			this.success("删除PDF模板成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除PDF模板失败",null);
		}
	}
}
