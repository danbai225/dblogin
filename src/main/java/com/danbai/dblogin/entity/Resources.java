package com.danbai.dblogin.entity;

/**
 * @author danbai
 * @date 2020-02-04 16:43
 */
public class Resources {
    private int code;
    private Object data;
    private String msg;
    public static String OK = "成功";
    public static String ERR = "错误";
    public static String KONG = "值为空";

    public Resources(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public Resources setCode(int code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Resources setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Resources setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static Resources Ok() {
        return new Resources(200, null, OK);
    }

    public static Resources Err() {
        return new Resources(200, null, ERR);
    }
}
