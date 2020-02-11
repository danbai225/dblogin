package com.danbai.dblogin.entity;

import javax.persistence.*;

/**
 * @author danbai
 */
public class User {
    @Id
    private String username;
    private String password;
    public static String NOTKONG = "账号密码不能为空";
    public static String ERR = "账号密码错误";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}