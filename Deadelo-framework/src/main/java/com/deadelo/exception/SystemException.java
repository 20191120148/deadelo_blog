package com.deadelo.exception;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/22  0:33
 */


import com.deadelo.domain.Enum.AppHttpCodeEnum;

public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
