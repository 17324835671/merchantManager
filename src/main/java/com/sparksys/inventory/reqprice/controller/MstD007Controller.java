package com.sparksys.inventory.reqprice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparksys.common.controller.CommonController;
import com.sparksys.common.entity.MstD002Material;
import com.sparksys.common.entity.MstD007ReqPriceH;
import com.sparksys.common.entity.PageBean;
import com.sparksys.common.entity.SysUser;
import com.sparksys.common.utils.DateUtil;
import com.sparksys.inventory.material.service.IMstD002MaterialService;
import com.sparksys.inventory.reqprice.service.IMstD007Service;
import com.sparksys.system.user.service.ISysUserService;
@Controller
public class MstD007Controller extends CommonController{

	@Resource
	private IMstD002MaterialService mstD002Service;
	
	@Resource
	private IMstD007Service mstD007Service;
	/**
	 * 用户接口
	 */
	@Resource
	private ISysUserService userService;
	
	@RequestMapping(value ="/queryMstD007InfoList")
	public String queryMstD007InfoList() throws Exception {
		int pageNum = 0;
		String currentNum = this.getRequest().getParameter("currentNum");
		String reqpName = this.getRequest().getParameter("reqpName");
		String effMonthFrom = this.getRequest().getParameter("effMonthFrom");
		String effMonthTo = this.getRequest().getParameter("effMonthTo");
		if (currentNum!=null&&!"".equals(currentNum)) {
			pageNum=Integer.valueOf(currentNum);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("reqpName", reqpName);
		map.put("effMonthFrom", effMonthFrom);
		map.put("effMonthTo", effMonthTo);
		PageBean<MstD007ReqPriceH> list = mstD007Service.findMstD007ReqPriceHList(pageNum, 10, map);
		this.getRequest().setAttribute("mstD007List", list.getItems());
		this.getRequest().setAttribute("pageTag", list);
		return "reqprice/reqpriceList";
	}
	@RequestMapping(value ="/queryMstD007InfoSearch",method = RequestMethod.GET)
	public String queryMstD007InfoSearch() throws Exception {
		return "reqprice/reqpriceSearch";
	}
	@RequestMapping(value ="/addMstD007ReqPriceHForworrd")
	public String addMstD007ReqPriceHForworrd() throws Exception {
		List<MstD002Material> materialList = mstD002Service.findMstD002MaterialList();
		this.getRequest().setAttribute("materialList", materialList);
		return "reqprice/reqpriceAdd";
	}
	/**
	 * 保存商品定价组信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveMstD007ReqPriceH")
	@ResponseBody
	public void saveMstD007ReqPriceH() throws Exception {
		SysUser sessionUserBean = getSessionUserBean();
		String reqpName= this.getRequest().getParameter("reqpName");
		String effMonthFrom= this.getRequest().getParameter("effMonthFrom");
		String effMonthTo= this.getRequest().getParameter("effMonthTo");
		String materialId= this.getRequest().getParameter("materialId");
		String stopFlag= this.getRequest().getParameter("stopFlag");
		try {
			MstD007ReqPriceH reqPriceH = new MstD007ReqPriceH();
			reqPriceH.setMaterialId(Long.valueOf(materialId));
			reqPriceH.setReqpName(reqpName);
			reqPriceH.setEffMonthFrom(DateUtil.stringToDate(effMonthFrom));
			reqPriceH.setEffMonthTo(DateUtil.stringToDate(effMonthTo));
			reqPriceH.setStopFlag(Long.valueOf(stopFlag));
			reqPriceH.setCdPerson(sessionUserBean.getUserId());
			reqPriceH.setUpPerson(sessionUserBean.getUserId());
			mstD007Service.saveMstD007ReqPriceH(reqPriceH);
			this.success("新增商品定价组成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("新增商品定价组失败",null);
		}
	}
	
	/**
	 * 修改商品定价组信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD007ReqPriceH")
	@ResponseBody
	public void updateMstD007ReqPriceH() throws Exception {
		SysUser sessionUserBean = getSessionUserBean();
		String reqpUid= this.getRequest().getParameter("reqpUid");
		String reqpName= this.getRequest().getParameter("reqpName");
		String effMonthFrom= this.getRequest().getParameter("effMonthFrom");
		String effMonthTo= this.getRequest().getParameter("effMonthTo");
		String materialId= this.getRequest().getParameter("materialId");
		String stopFlag= this.getRequest().getParameter("stopFlag");
		try {
			MstD007ReqPriceH reqPriceH = mstD007Service.findById(Long.valueOf(reqpUid));
			reqPriceH.setMaterialId(Long.valueOf(materialId));
			reqPriceH.setReqpName(reqpName);
			reqPriceH.setEffMonthFrom(DateUtil.stringToDate(effMonthFrom));
			reqPriceH.setEffMonthTo(DateUtil.stringToDate(effMonthTo));
			reqPriceH.setStopFlag(Long.valueOf(stopFlag));
			reqPriceH.setUpPerson(sessionUserBean.getUserId());
			mstD007Service.updateMstD007ReqPriceH(reqPriceH);
			this.success("修改商品定价组成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("修改商品定价组失败",null);
		}
	}
	/**
	 * 查看商品定价组信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/viewMstD007ReqPriceHInfo")
	public String viewMstD007ReqPriceHInfo() throws Exception {
		String reqpUid= this.getRequest().getParameter("reqpUid");
		MstD007ReqPriceH reqPriceH = mstD007Service.findById(Long.valueOf(reqpUid));
		this.getRequest().setAttribute("reqPriceH", reqPriceH);
		return "reqprice/reqpriceView";
		
	}
	/**
	 * 修改商品定价组信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMstD007ReqPriceHForword")
	public String updateMstD007ReqPriceHForword() throws Exception {
		String reqpUid= this.getRequest().getParameter("reqpUid");
		MstD007ReqPriceH reqPriceH = mstD007Service.findById(Long.valueOf(reqpUid));
		List<MstD002Material> materialList = mstD002Service.findMstD002MaterialList();
		this.getRequest().setAttribute("reqPriceH", reqPriceH);
		this.getRequest().setAttribute("materialList", materialList);
		return "reqprice/reqpriceUpdate";
		
	}
	/**
	 * 删除商品定价组信息
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteMstD007ReqPriceH")
	@ResponseBody
	public void deleteMstD007ReqPriceH() throws Exception {
		String reqpUid= this.getRequest().getParameter("reqpUid");
		try {
			mstD007Service.deleteMstD007ReqPriceH(Long.valueOf(reqpUid));
			this.success("删除商品定价组成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			this.error("删除商品定价组失败",null);
		}
	}
}
