package com.kronos.newsapp.tool;

public enum Code {
    /**
     * 状态码
     */
    OK(200,"执行成功"),
    FAILED(404,"操作失败"),
    NO_USER(201,"用户不存在"),
    PASS_ERR(202,"密码错误"),
    USER_EXIST(203,"用户已存在");
    Code(int code, String description){

    }
}
