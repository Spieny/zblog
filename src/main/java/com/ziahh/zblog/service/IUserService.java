package com.ziahh.zblog.service;

import com.ziahh.zblog.pojo.DTO.UserLoginDTO;
import com.ziahh.zblog.pojo.DTO.UserRegisterDTO;
import com.ziahh.zblog.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ziahh.zblog.pojo.VO.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Ziahh
 * @since 2024-09-19
 */
public interface IUserService extends IService<User> {

    /*
    用户注册
     */
    void register(UserRegisterDTO userRegisterDTO);

    UserVO userLogin(UserLoginDTO userLoginDTO);
}
