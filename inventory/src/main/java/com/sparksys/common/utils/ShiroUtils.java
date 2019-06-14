package com.sparksys.common.utils;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ShiroUtils.class);
	
	public static boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            logger.debug("shiro工具类【wyait-manager-->ShiroFilterUtils.isAjax】当前请求,为Ajax请求");
            return Boolean.TRUE;
        }
        logger.debug("shiro工具类【wyait-manager-->ShiroFilterUtils.isAjax】当前请求,非Ajax请求");
        return Boolean.FALSE;
    }
	public static void main(String[] args) {
		AtomicInteger passwoedInteger = new AtomicInteger(0);
		for (int i = 0; i < 10; i++) {
			System.out.println(passwoedInteger.incrementAndGet());
		}
		System.out.println(passwoedInteger.intValue());
	}
}
