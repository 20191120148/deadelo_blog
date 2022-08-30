package com.deadelo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deadelo.domain.entity.User;
import com.deadelo.vo.ResponseResult;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-07-24 22:11:29
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}
