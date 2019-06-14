package com.sparksys.common.suport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sparksys.common.utils.ShiroUtils;
/**
 * 全局统一异常处理
 * 
 * @project: inventory
 * @author: zhouxinlei
 * @time：2018年9月6日 @version：ver 1.1
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	public static final String DEFAULT_ERROR_VIEW = "error/error";
	public static final String NOT_Found_VIEW = "error/404";
	public static final String UNAUTHORIZED = "error/unauthorized";
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ModelAndView errorHandler(HttpServletRequest request,HttpServletResponse response, Exception e) {
		LOGGER.error("============拦截全局异常{}==================",e);
		ModelAndView mv=new ModelAndView();
		//1 获取错误状态码
		HttpStatus httpStatus=getStatus(request);
		//2 返回错误页面
		String status=getMessage(httpStatus);
		//3 将错误信息放入mv中
		if(!ShiroUtils.isAjax(request)){
			//不是异步请求
			mv.setViewName(status);
			request.setAttribute("message", e.getMessage());
			LOGGER.debug(getClass().getName() + ".errorHandler】统一异常处理：普通请求。");
		}
		return mv;
	}
	
	/**
	 * 
	 * @描述：获取错误状态码
	 * @创建人：wyait
	 * @创建时间：2018年5月31日 下午2:19:39
	 * @param request
	 * @return
	 */
	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try {
			return HttpStatus.valueOf(statusCode);
		}
		catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	/**
	 * 
	 * @描述：根据error状态码，返回不同的错误提示信息
	 * @创建人：wyait
	 * @创建时间：2018年5月31日 下午2:52:50
	 * @param httpStatus
	 * @return
	 */
	@SuppressWarnings({ "unlikely-arg-type", "static-access" })
	private String getMessage(HttpStatus httpStatus) {
		String code="";
		if(httpStatus.is4xxClientError()){
			//4开头的错误状态码
			if("400".equals(httpStatus.BAD_REQUEST)||"404".equals(httpStatus.NOT_FOUND)){
				code=NOT_Found_VIEW;
			}else if("403".equals(httpStatus.FORBIDDEN)||"401".equals(httpStatus.UNAUTHORIZED)){
				code=UNAUTHORIZED;
			}
		}else if(httpStatus.is5xxServerError()){
			//5开头的错误状态码
			if("500".equals(httpStatus.INTERNAL_SERVER_ERROR)){
				code=DEFAULT_ERROR_VIEW;
			}
		}
		return code;
	}
}
