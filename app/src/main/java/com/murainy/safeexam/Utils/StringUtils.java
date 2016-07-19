package com.murainy.safeexam.Utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    //判断是否有中文字符
    static String regEx = "[\u4e00-\u9fa5]";
    static Pattern pat = Pattern.compile(regEx);

    /**
     * 判断字符串中是否包含有中文文字
     */
    public static boolean isContainsChinese(String str) {
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }

    /**
     * String 转double类型(保留两位小数)
     */
    public static String convertToDouble(String str) {

        if (!TextUtils.isEmpty(str)) {
            try {
                double d = Double.parseDouble(str);
                if (d > 0) {
                    DecimalFormat format = new DecimalFormat("0.00");
                    String result = format.format(d);
                    return result;
                } else {
                    return "0.00";
                }
            } catch (Exception e) {
                return "0.00";
            }
        } else {
            return "0.00";
        }
    }

    /**
     * 提供字符串到字符串数组的转变,
     * 转变后的字符串以sStr作为分割符
     */
    public static String[] Str2Strs(String tStr, String sStr) {
        StringTokenizer st = new StringTokenizer(tStr, sStr);
        String[] reStrs = new String[st.countTokens()];
        int n = 0;
        while (st.hasMoreTokens()) {
            reStrs[n] = st.nextToken();
            n++;
        }
        return reStrs;
    }

    /**
     * 将String 替换操作，将str1替换为str2 *
     */
    public static String replace(String str, String str1, String str2) {
        int n = -1;
        String subStr = "";
        String re = "";
        if ((n = str.indexOf(str1)) > -1) {
            subStr = str.substring(n + str1.length(), str.length());
            re = str.substring(0, n) + str2 + replace(subStr, str1, str2);
        } else {
            re = str;
        }
        return re;
    }

    /**
     * 判断邮箱地址的正确性
     */
    public static boolean isMail(String string) {
        if (null != string) {
            if (string.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断手机号码的正确性
     */
    public static boolean isMobileNumber(String mobiles) {

        if (null != mobiles) {
            Pattern p = Pattern
                    .compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } else {
            return false;
        }
    }

    /**
     * 检测身份证号格式是否正确
     */
    public static boolean checkIdNum(String idNum) {
        String regExp = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(idNum);
        return m.find();
    }
}