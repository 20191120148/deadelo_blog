package com.deadelo.service.impl;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/8/11  21:26
 */

import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.Enum.AppHttpCodeEnum;
import com.deadelo.domain.entity.LoginUser;
import com.deadelo.domain.entity.User;
import com.deadelo.exception.SystemException;
import com.deadelo.service.LoginService;
import com.deadelo.utils.BeanCopyUtils;
import com.deadelo.utils.JwtUtil;
import com.deadelo.utils.RedisCache;
import com.deadelo.vo.ResponseResult;
import com.deadelo.vo.UserInfoVo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationProvider authenticationProvider;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationProvider.authenticate(authenticationToken);

        //判断是否认证通过

        if (Objects.isNull(authenticate)){
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        //获取userId生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String jwt = JwtUtil.createJWT(loginUser.getUser().getId().toString());

        //将user存入redis
        redisCache.setCacheObject(SystemConstants.ADMIN_LOGIN_PREFIX+loginUser.getUser().getId().toString(),loginUser);

//        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser, UserInfoVo.class);

        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }
}
