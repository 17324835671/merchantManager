package com.sparksys.common.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bouncycastle.util.encoders.Hex;

import com.sparksys.common.sm.SM3Digest;
/** 
 *  @application 
 *  @author zhouxinlei 
 *  @time 2019年1月29日 下午3:48:23 
 *  @version 0.1 
 */

public class UtilTools {

	public static final String FTP_SERVER_PATH = "ftp://118.25.95.95/";
	public static final String FTP_SERVER_USER = "zhouxinlei";
	public static final String FTP_SERVER_PASSWORD = "zxl298828@";
	
	/**
     * SM3加密算法
     * 
     * @param source 源字符串
     * @return SM3加密串
     * @author Simon
     */
    public static String SM3(String source) {
        if (source == null || source.length() == 0) {
            System.err.println("警告 ： 空字符串不可以作SM3加密 !");
            return null;
        }
        byte[] md = new byte[32];
        byte[] msg = source.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg, 0, msg.length);
        sm3.doFinal(md, 0);
        return new String(Hex.encode(md));
    }
    public static void main(String[] args) {
		System.out.println(SM3("123456"));
	}
	/**
	 * 判断是否是数字类型
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
	/**
	 * double去除多余0
	 * @param number
	 * @return
	 */
    public static String formatNumeric(Double number) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.setGroupingUsed(false);
        return df.format(number);
    }
    /**
	 * double去除多余0
	 * @param number
	 * @return
	 */
    public static String formatNumericCurrency(Double number) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        return df.format(number);
    }
    
    public static String GeneratedValue(String rule){
		return rule+"-"+new SimpleDateFormat("HHmmss").format(new Date());
		
	}
	
	public static String GeneratedValue1(){
		return new SimpleDateFormat("HHmmss").format(new Date());
	}
	
}
