package com.ziahh.zblog.pojo.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ziahh
 * @description 用户注册请求
 * @since 2024/9/19
 */
@Setter
@Getter
public class UserLoginDTO {

    private String email;

    private String password;

    // 验证码标识
    private String captchaKey;

    // 验证码
    private String captcha;
}
