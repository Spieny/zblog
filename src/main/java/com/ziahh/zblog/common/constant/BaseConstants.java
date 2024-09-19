package com.ziahh.zblog.common.constant;

/**
 * @Title: Constants
 * @Author Ziahh
 * @Package com.aquachat.constants
 * @Date 2024/4/24 下午3:41
 * @description: 基础常量
 */
public class BaseConstants {

    public static final String REDIS_KEY_CAPTCHA = "aquacode:captcha:";
    public static final String REDIS_KEY_ACCESSLIMIT = "aquacode:accesslimit:";

    public static final Long REDIS_TIME_1MIN = 60L;
    public static final Long REDIS_EXPIRE_TIME_CAPTCHA = 300L;
    public static final Long REDIS_EXPIRE_TIME_ACCESS = 60L;
    public static final Long REDIS_KEY_EXPIRES_DAY = REDIS_TIME_1MIN * 60 * 24;
    public static final Long ONE_MINUTE = 60 * 1000L;


    public static final Long TEN_SECONDS = 10 * 1000L;
    public static final Long HALF_AN_HOUR = 1000 * 60 * 10L;

    public static final Integer LENGTH_ELEVEN = 11;

    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";
    public static final String BAN_ROLE = "ban";


    public static final String MD5_SALT = "aquacode_md5";
    public static final String FILE_FOLDER_FILE = "/file/";

    public static final String FILE_FOLDER_AVATAR_NAME = "avatar/";
    public static final String IMAGE_SUFFIX = ".png";
    public static final String COVER_IMAGE_SUFFIX = "_cover.png";


}
