package com.deadelo.handler.security;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/22  0:15
 */

import com.alibaba.fastjson.JSON;
import com.deadelo.domain.Enum.AppHttpCodeEnum;
import com.deadelo.utils.WebUtils;
import com.deadelo.vo.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        e.printStackTrace();

        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        //响应给前端
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
    }
}
