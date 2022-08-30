package com.deadelo.domain.Enum;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/9  20:56
 */

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    COMMENT_NOT_NULL(506,"评论内容不能为空"),
    FILE_TYPE_ERROR(507,"图片格式错误"),
    USERNAME_NOT_NULL(508,"用户名不为空"),
    NICKNAME_NOT_NULL(509,"昵称不为空"),
    PASSWORD_NOT_NULL(510,"密码不为空"),
    EMAIL_NOT_NULL(511,"邮箱不为空"),
    PASSWORD_EXIST(512,"密码相同"),
    NICKNAME_EXIST(513,"昵称已存在");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
