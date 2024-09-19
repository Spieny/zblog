package com.ziahh.zblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ziahh.zblog.common.constant.MsgConstants;
import com.ziahh.zblog.common.enums.UserRole;
import com.ziahh.zblog.common.exception.BusinessException;
import com.ziahh.zblog.common.properties.JwtProperties;
import com.ziahh.zblog.common.result.Result;
import com.ziahh.zblog.common.utils.NewJWTUtil;
import com.ziahh.zblog.common.utils.StringUtils;
import com.ziahh.zblog.pojo.DTO.UserLoginDTO;
import com.ziahh.zblog.pojo.DTO.UserRegisterDTO;
import com.ziahh.zblog.pojo.User;
import com.ziahh.zblog.mapper.UserMapper;
import com.ziahh.zblog.pojo.VO.UserVO;
import com.ziahh.zblog.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Ziahh
 * @since 2024-09-19
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private JwtProperties jwtProperties;

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        if(!StringUtils.isValidEmail(userRegisterDTO.getEmail())){
            throw new BusinessException(MsgConstants.EMAIL_NOT_VALID);
        }
        if(!StringUtils.isValidUsername(userRegisterDTO.getUsername())){
            throw new BusinessException(MsgConstants.USERNAME_NOT_VALID);
        }
        //检查用户是否已注册
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,userRegisterDTO.getEmail());
        User testUser = userMapper.selectOne(queryWrapper);
        if (testUser != null) {throw new BusinessException(MsgConstants.USER_ALREADY_REGISTERED);}
        //校验参数
        if(userRegisterDTO.getEmail().isBlank() || userRegisterDTO.getPassword().isBlank() || userRegisterDTO.getUsername().isBlank()){
            throw new BusinessException(MsgConstants.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO,user);

        //TODO 配置MD5加盐的配置文件
        user.setPassword(DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes()));

        log.info("用户成功注册：{}",user);
        userMapper.insert(user);
    }

    @Override
    public UserVO userLogin(UserLoginDTO userLoginDTO) {
        //设置密码MD5编码,方便查询
        userLoginDTO.setPassword(DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes()));
        //根据用户名、邮箱和密码查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,userLoginDTO.getEmail()).eq(User::getPassword,userLoginDTO.getPassword());
        User emailLoginUser = userMapper.selectOne(queryWrapper);

        LambdaQueryWrapper<User> queryUsernameWrapper = new LambdaQueryWrapper<>();
        queryUsernameWrapper.eq(User::getUsername,userLoginDTO.getEmail()).eq(User::getPassword,userLoginDTO.getPassword());
        User usernameLoginUser = userMapper.selectOne(queryUsernameWrapper);

        User user = null;
        if(emailLoginUser == null && usernameLoginUser == null){
            throw new BusinessException(MsgConstants.USERNAME_OR_PASSWORD_ERROR);
        } else if(emailLoginUser != null){
            user = emailLoginUser;
        } else if(usernameLoginUser != null){
            user = usernameLoginUser;
        }
        //用户属于封禁状态
        UserRole role = UserRole.getUserRoleByValue(user.getRole());
        if(role == null){
            throw new BusinessException(MsgConstants.USER_ROLE_ERROR);
        }
        if(role.equals(UserRole.BAN)){
            throw new BusinessException(MsgConstants.USER_BANNED);
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);

        //为用户生成Token
        String token = NewJWTUtil.genAccessToken(user.getId().toString(),user.getUsername(),jwtProperties.getUserSecretKey());
        userVO.setToken(token);
        log.info("用户成功登录 ： {}",userVO);
        return userVO;
    }
}
