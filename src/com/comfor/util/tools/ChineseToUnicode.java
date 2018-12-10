package com.comfor.util.tools;

/**
 * 汉字转换成Unicode编码
 * @author 三米阳光 2018年8月10日
 */
public class ChineseToUnicode {
	
	public static String gbEncoding(String gbString) {   //gbString = "测试"  
        char[] utfBytes = gbString.toCharArray();   //utfBytes = [测, 试]  
        String unicodeBytes = "";     
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {     
            String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串  
              if (hexB.length() <= 2) {     
                  hexB = "00" + hexB;     
             }     
             unicodeBytes = unicodeBytes + "\\u" + hexB;     
        }     
        System.out.println("unicodeBytes is: " + unicodeBytes);     
        return unicodeBytes;     
    }

}
