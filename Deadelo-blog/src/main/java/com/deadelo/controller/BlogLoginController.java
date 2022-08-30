package com.deadelo.controller;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/21  16:46
 */

import com.deadelo.domain.Enum.AppHttpCodeEnum;
import com.deadelo.domain.entity.User;
import com.deadelo.exception.SystemException;
import com.deadelo.service.BlogLoginService;
import com.deadelo.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            //提示 必须要用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }


    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
