package com.deadelo.service.impl;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/21  17:04
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.deadelo.domain.entity.LoginUser;
import com.deadelo.domain.entity.User;
import com.deadelo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查询到该用户，如果不存在抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        //返回用户信息

        //TODO 查询权限信息封装
        return new LoginUser(user);
    }
}
