package com.deadelo.service;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/21  16:49
 */

import com.deadelo.domain.entity.User;
import com.deadelo.vo.ResponseResult;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
