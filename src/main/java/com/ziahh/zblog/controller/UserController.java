package com.ziahh.zblog.controller;


import com.wf.captcha.GifCaptcha;
import com.ziahh.zblog.common.constant.BaseConstants;
import com.ziahh.zblog.common.constant.MsgConstants;
import com.ziahh.zblog.common.exception.BusinessException;
import com.ziahh.zblog.common.properties.AppProperties;
import com.ziahh.zblog.common.result.Result;
import com.ziahh.zblog.common.utils.RedisUtils;
import com.ziahh.zblog.pojo.DTO.UserLoginDTO;
import com.ziahh.zblog.pojo.DTO.UserRegisterDTO;
import com.ziahh.zblog.pojo.VO.UserVO;
import com.ziahh.zblog.service.IUserService;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户 前端控制器
 *
 * @author Ziahh
 * @since 2024-09-19
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private AppProperties appProperties;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private IUserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result userRegister(UserRegisterDTO userRegisterDTO){
        if(userRegisterDTO == null){
            return Result.error(MsgConstants.PARAMS_ERROR);
        }
        startCaptcha(userRegisterDTO.getCaptchaKey(), userRegisterDTO.getCaptcha());
        userService.register(userRegisterDTO);
        return Result.success(MsgConstants.USER_REGISTER_SUCCESS);
    }

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    //@AccessLimit(limit = 5,duration = 60L)
    public Result<UserVO> login(@RequestBody UserLoginDTO userLoginDTO){
        if(userLoginDTO == null){
            return Result.error(MsgConstants.PARAMS_ERROR);
        }
        //检查验证码
        startCaptcha(userLoginDTO.getCaptchaKey(), userLoginDTO.getCaptcha());
        UserVO userVO = userService.userLogin(userLoginDTO);
        return Result.success(userVO);
    }

    /**
     * 用户获取验证码
     * @return
     */
    @ApiOperation("获取验证码")
    @GetMapping("/captcha")
    public Result captcha(){
        GifCaptcha captcha = new GifCaptcha(100,43,4);
        String code = captcha.text();
        String checkKey = UUID.randomUUID().toString();

        log.info("获取验证码：{},UUID = {}",code,checkKey);

        //验证码存入缓存
        redisUtils.set(BaseConstants.REDIS_KEY_CAPTCHA + checkKey,code, BaseConstants.REDIS_EXPIRE_TIME_CAPTCHA);

        String checkCodeBase64 = captcha.toBase64();
        Map<String,String> map = new HashMap<>();
        map.put("captchaImg",checkCodeBase64);
        map.put("captchaKey",checkKey);

        return Result.success(map);
    }

    /**
     * 验证码校验
     */
    private boolean startCaptcha(String captchaKey,String captcha){
        //如果处于开发者模式，跳过验证码
        if(appProperties.getActive().equals("dev")){
            log.info("处于开发者模式，已跳过验证码");
            return true;
        }
        //检验验证码是否正确
        if(captcha == null || captchaKey == null){
            throw new BusinessException(MsgConstants.CAPTCHA_ERROR);
        }
        String correctCode = (String) redisUtils.get(BaseConstants.REDIS_KEY_CAPTCHA + captchaKey);
        try {
            if (!captcha.equalsIgnoreCase(correctCode)){
                log.info("验证码错误: Required:{} -> Got:{}",correctCode,captcha);
                //验证码错误，抛出异常
                throw new BusinessException(MsgConstants.CAPTCHA_ERROR);
            } else {
                return true;
            }
        } finally {
            //无论验证码正确与否，删除旧验证码
            log.info("删除旧验证码，key = {}",BaseConstants.REDIS_KEY_CAPTCHA + captchaKey);
            redisUtils.remove(BaseConstants.REDIS_KEY_CAPTCHA + captchaKey);
        }
    }

}
