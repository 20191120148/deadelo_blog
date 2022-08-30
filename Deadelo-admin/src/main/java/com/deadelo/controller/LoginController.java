package com.deadelo.controller;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/8/11  21:23
 */

import com.deadelo.domain.Enum.AppHttpCodeEnum;
import com.deadelo.domain.entity.LoginUser;
import com.deadelo.domain.entity.User;
import com.deadelo.exception.SystemException;
import com.deadelo.service.LoginService;
import com.deadelo.service.MenuService;
import com.deadelo.service.RoleService;
import com.deadelo.utils.BeanCopyUtils;
import com.deadelo.utils.SecurityUtils;
import com.deadelo.vo.AdminUserInfoVo;
import com.deadelo.vo.ResponseResult;
import com.deadelo.vo.UserInfoVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping
public class LoginController {

    @Resource
    private LoginService loginService;


    @Resource
    private MenuService menuService;


    @Resource
    private RoleService roleService;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            //提示 必须要用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }


    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectParamsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = null;
//        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //封装数据返回
        return ResponseResult.okResult(new AdminUserInfoVo(perms,roleKeyList, BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class)));
    }
}
