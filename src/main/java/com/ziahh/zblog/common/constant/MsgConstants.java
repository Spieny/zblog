package com.ziahh.zblog.common.constant;

/**
 * @author Ziahh
 * @description 消息常量
 * @since 2024/9/19
 */
public class MsgConstants {

    public static final String PARAMS_ERROR = "参数错误！";

    /**
     * 用户类
     */
    public static final String USER_REGISTER_SUCCESS = "注册成功";
    public static final String USER_ALREADY_REGISTERED = "用户已注册";
    public static final String USER_ALREADY_BANNED = "用户已经封禁，无法继续操作。";
    public static final String USERNAME_OR_PASSWORD_ERROR = "用户名或密码错误";
    public static final String USER_OLD_PASSWORD_ERROR = "旧密码输入错误";
    public static final String USER_BANNED = "用户已封禁";
    public static final String USER_ROLE_ERROR = "用户角色异常";
    public static final String USER_NOT_FOUND = "用户不存在";
    public static final String EMAIL_NOT_VALID = "注册邮箱不合法";
    public static final String USERNAME_NOT_VALID = "用户名不合法";
    public static final String USER_PERMISSION_DENIED = "用户权限不足";
    public static final String EMAIL_ALREADY_USED = "该邮箱已被使用，请换一个试试。";

    public static final String DATABASE_REDIS_ACCESS_ERROR = "缓存数据库发生异常";

    public static final String CAPTCHA_ERROR = "验证码输入错误";

    public static final String UPLOAD_FAILED = "上传文件失败";
}
