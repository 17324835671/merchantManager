package com.sparksys.common.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/** 
 *  @application 
 *  @author zhouxinlei 
 *  @time 2019年1月29日 下午3:45:39 
 *  @version 0.1 
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	private final String successUrl = "/index";

	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		WebUtils.issueRedirect(request, response, successUrl, null, true);
	}
}
