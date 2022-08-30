package com.deadelo.service.impl;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/21  16:49
 */

import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.Enum.AppHttpCodeEnum;
import com.deadelo.domain.entity.LoginUser;
import com.deadelo.domain.entity.User;
import com.deadelo.exception.SystemException;
import com.deadelo.service.BlogLoginService;
import com.deadelo.utils.BeanCopyUtils;
import com.deadelo.utils.JwtUtil;
import com.deadelo.utils.RedisCache;
import com.deadelo.vo.BlogUserLoginVo;
import com.deadelo.vo.ResponseResult;
import com.deadelo.vo.UserInfoVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过

        if (Objects.isNull(authenticate)){
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        //获取userId生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        //把用户信息存入redis

        redisCache.setCacheObject(SystemConstants.BLOG_LOGIN_PREFIX+id,loginUser);



        //把token和UserInfo封装返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(),UserInfoVo.class);
        return ResponseResult.okResult(new BlogUserLoginVo(jwt,userInfoVo));
    }

    @Override
    public ResponseResult logout(){

        //解析token获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //根据userId删除redis中的用户信息
        redisCache.deleteObject(SystemConstants.BLOG_LOGIN_PREFIX + loginUser.getUser().getId());
//        if(!LoginFlag){
//            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
//        }else {
            return ResponseResult.okResult();

    }
}
