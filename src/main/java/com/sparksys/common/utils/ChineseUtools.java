package com.sparksys.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;  
/**
* Title:判断url是否是中午乱码  解决乱码问题
* Description:
* @author wangyong
* @date 2017年8月16日
* @version 1.0
*/  
public class ChineseUtools {  
  
    private static boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }  
      
    public static boolean isMessyCode(String strName) {  
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");  
        Matcher m = p.matcher(strName);  
        String after = m.replaceAll("");  
        String temp = after.replaceAll("\\p{P}", "");  
        char[] ch = temp.trim().toCharArray();  
        float chLength = 0 ;  
        float count = 0;  
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if (!Character.isLetterOrDigit(c)) {  
                if (!isChinese(c)) {  
                    count = count + 1;  
                }  
                chLength++;   
            }  
        }  
        float result = count / chLength ;  
        if (result > 0.4) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
      
    public static String toChinese(Object msg){  
        String tempMsg = (String) msg ;  
        if(isMessyCode(tempMsg)){  
            try {  
                return new String(tempMsg.getBytes("ISO8859-1"), "UTF-8");  
            } catch (Exception e) {  
            }  
        }  
        return tempMsg ;   
    }  
    public static boolean isMessyCode1(String strName) {
        try {
            Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
            Matcher m = p.matcher(strName);
            String after = m.replaceAll("");
            String temp = after.replaceAll("\\p{P}", "");
            char[] ch = temp.trim().toCharArray();

            int length = (ch != null) ? ch.length : 0;
            for (int i = 0; i < length; i++) {
                char c = ch[i];
                if (!Character.isLetterOrDigit(c)) {
                    String str = "" + ch[i];
                    if (!str.matches("[\u4e00-\u9fa5]+")) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}  
