package com.danbai.dblogin.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.danbai.dblogin.client.entity.User;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * @author DanBai
 */
public class DBlogin {
    private String passwordManagement;
    private String serviceURL;
    private static String HTTP="http://";
    private static String HTTPS="https://";
    private static String OKLOGIN="登陆成功";
    public DBlogin(String passwordManagement, String serviceURL) {

        if(serviceURL.indexOf(HTTP)==-1&&serviceURL.indexOf(HTTPS)==-1){
            serviceURL=HTTP+serviceURL;
        }
        if(passwordManagement==null){
            passwordManagement="";
        }
        this.passwordManagement = passwordManagement;
        this.serviceURL = serviceURL;
    }

    /**
     * 登陆  成功返回token
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username,String password){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(serviceURL+"/login?passwordManagement="+passwordManagement+"&username="+username+"&password="+password).build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            String msg = jsonObject.getString("msg");
            if(msg.equals(OKLOGIN)){
                return jsonObject.getString("data");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 登陆  成功返回token
     * @param user 登陆信息
     * @return token
     */
    public String login(User user){
        return login(user.getUsername(),user.getPassword());
    }
    public String getPasswordManagement() {
        return passwordManagement;
    }

    public void setPasswordManagement(String passwordManagement) {
        this.passwordManagement = passwordManagement;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }
}
