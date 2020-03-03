package cn.p00q.dblogin.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.p00q.dblogin.client.entity.User;
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
    private static String HTTP = "http://";
    private static String HTTPS = "https://";
    private static String OKLOGIN = "登陆成功";
    public static String OK = "成功";

    public DBlogin(String passwordManagement, String serviceURL) {

        if (serviceURL.indexOf(HTTP) == -1 && serviceURL.indexOf(HTTPS) == -1) {
            serviceURL = HTTP + serviceURL;
        }
        if (passwordManagement == null) {
            passwordManagement = "";
        }
        this.passwordManagement = passwordManagement;
        this.serviceURL = serviceURL;
    }

    /**
     * 登陆  成功返回token
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username, String password) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(serviceURL + "/login?passwordManagement=" + passwordManagement + "&username=" + username + "&password=" + password).build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            String msg = jsonObject.getString("msg");
            if (msg.equals(OKLOGIN)) {
                return jsonObject.getString("data");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 登陆  成功返回token
     *
     * @param user 登陆信息
     * @return token
     */
    public String login(User user) {
        return login(user.getUsername(), user.getPassword());
    }

    /**
     * 退出登陆
     *
     * @param token
     */
    public void logout(String token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(serviceURL + "/logout?passwordManagement=" + passwordManagement + "&token=" + token).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改token过期时间 单位秒
     *
     * @param token
     * @param time
     */
    public void tokenTime(String token, long time) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(serviceURL + "/tokenTime?passwordManagement=" + passwordManagement + "&token=" + token + "&time=" + time).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return 消息
     */
    public String register(String username, String password) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(serviceURL + "/register?passwordManagement=" + passwordManagement + "&username=" + username + "&password=" + password).build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            return jsonObject.getString("msg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    public String register(User user) {
        return register(user.getUsername(), user.getPassword());
    }

    /**
     * 根据token获取用户名
     *
     * @param token
     * @return
     */
    public String username(String token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(serviceURL + "/username?passwordManagement=" + passwordManagement + "&token=" + token).build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());

            String msg = jsonObject.getString("msg");
            if (msg.equals(OK)) {
                return jsonObject.getString("data");
            }
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除用户
     *
     * @param username
     */
    public void delete(String username) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(serviceURL + "/delete?passwordManagement=" + passwordManagement + "&username=" + username).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean user(String username){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(serviceURL + "/user?passwordManagement=" + passwordManagement + "&username=" + username).build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            String msg = jsonObject.getString("msg");
            if (msg.equals(OK)) {
                return jsonObject.getBoolean("data");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean newPass(User user,String newPass){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(serviceURL + "/newPass?passwordManagement=" + passwordManagement + "&username=" +user.getUsername()+"&password="+user.getPassword()+"&newPass="+newPass).build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            String msg = jsonObject.getString("msg");
            if (msg.equals(OK)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
