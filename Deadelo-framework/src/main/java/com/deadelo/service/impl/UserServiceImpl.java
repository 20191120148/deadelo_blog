package com.deadelo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deadelo.domain.Enum.AppHttpCodeEnum;
import com.deadelo.domain.entity.User;
import com.deadelo.exception.SystemException;
import com.deadelo.mapper.UserMapper;
import com.deadelo.service.UserService;
import com.deadelo.utils.BeanCopyUtils;
import com.deadelo.utils.SecurityUtils;
import com.deadelo.vo.ResponseResult;
import com.deadelo.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import java.util.Objects;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-07-24 22:11:29
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult userInfo() {
        //获取用户当前id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装user信息为userVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        columnIsNull(user);

        //对数据进行是否存在判断
        columnExist(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        return  ResponseResult.okResult();
    }





    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email);

        return count(queryWrapper)>0;
    }

    private boolean passwordExist(String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPassword,password);

        return count(queryWrapper)>0;
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName,nickName);

        return count(queryWrapper)>0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);

        return count(queryWrapper)>0;
    }

    private void columnIsNull(User user){
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
    }

    private void columnExist(User user){
        if (userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        if (emailExist(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
    }

}

