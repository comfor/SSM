package com.comfor.util.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断字体串中是否包含有汉字
 * @author 三米阳光 2018年8月10日
 */
public class ContainChinese {

    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
