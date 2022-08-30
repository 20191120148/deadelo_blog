package com.deadelo.controller;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/30  15:00
 */

import com.deadelo.annotation.SystemLog;
import com.deadelo.domain.entity.User;
import com.deadelo.service.UserService;
import com.deadelo.vo.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }
    @SystemLog(businessName = "更新用户信息")
    @PutMapping("/userInfo")
    public ResponseResult updataUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

}
