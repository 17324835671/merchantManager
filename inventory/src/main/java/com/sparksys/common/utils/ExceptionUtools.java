package com.sparksys.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
/**
 *  @application 异常处理
 *  @author zhouxinlei 
 *  @time 2019年3月5日 上午9:22:25 
 *  @version 0.1
 */
public class ExceptionUtools {

	public static String getStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		try {
			throwable.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}

	public static String getExceptionAllinformation(Exception ex) {
		String sOut = "";
		StackTraceElement[] trace = ex.getStackTrace();
		for (StackTraceElement s : trace) {
			sOut += "\tat " + s + "\r\n";
		}
		return sOut;
	}

	public static String getExceptionAllinformation_01(Exception ex) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream pout = new PrintStream(out);
		ex.printStackTrace(pout);
		String ret = new String(out.toByteArray());
		pout.close();
		try {
			out.close();
		} catch (Exception e) {
		}
		return ret;
	}
}
