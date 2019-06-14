package com.sparksys.common.utils;
/**
 * 
*  @application name： 
*  @author: zhouxinlei
*  @time：2018年7月18日
*  @version：ver 1.1
 */
public class CommonProperties {
	/**
	 * 时间格式常量
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String DD = "dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
	/**
	 * code为0：成功	
	 */
	public final static int SUCCESS = 0;
	/**
	 * 
	 */
	public final static int FAIL = -1;
	/**
	 * code为2 ： 系统出错
	 */
	public final static int SYSTEM_ERROR = -2;
	
	public static final String UnknownAccountException = "帐号不存在，请核实后输入！";
	
	public static final String AccountException = "帐号或密码不正确！";
	 
	public static final String IncorrectCredentialsException = "密码不正确，请重新输入!";
	 
	public static final String SHIRO_IS_LOCK = "由于密码输入错误次数大于5次，帐号已经禁止登录！";
	 
	public static final String kaptchaValidateFailed = "验证码有误，请重新输入!";
	 
	public static final String SESSION_TIME_OUT = "会话已超时，请重新登录！";
	 
}
