package com.ziahh.zblog.common.utils;

/**
 * @author Ziahh
 * @description
 * @since 2024/5/18
 */
public class StringUtils {

    public static boolean isValidEmail(String str) {
        String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return str.matches(regex);
    }

    public static boolean isValidUsername(String userName) {
        if(userName==null || userName.length() < 6 || userName.length() > 32){
            return false;
        }
        return true;
    }
}
