package com.deadelo.handler.exception;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/22  0:37
 */

import com.deadelo.domain.Enum.AppHttpCodeEnum;
import com.deadelo.exception.SystemException;
import com.deadelo.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){

        //打印异常信息
        log.error("出现了异常！{}",e);

        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(), e.getMsg());

    }
//    @ExceptionHandler(Exception.class)
//    public ResponseResult ExceptionHandler(Exception e){
//
//        //打印异常信息
//        log.error("出现了异常！{}",e.getMessage());
//        //从异常对象中获取提示信息封装返回
//        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,e.getMessage());
//    }
}
