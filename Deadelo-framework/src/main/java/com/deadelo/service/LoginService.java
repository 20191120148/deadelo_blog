package com.deadelo.service;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/8/11  21:25
 */

import com.deadelo.domain.entity.User;
import com.deadelo.vo.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);
}
