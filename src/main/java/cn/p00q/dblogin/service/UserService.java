package cn.p00q.dblogin.service;

import cn.p00q.dblogin.entity.Resources;
import cn.p00q.dblogin.entity.User;

/**
 * @author Administrator
 */
public interface UserService {
    /**
     * 登陆
     *
     * @param user
     * @return
     */
    Resources login(User user);

    /**
     * 查询一个用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getUser(String username);

    /**
     * 退出登陆
     *
     * @param token token
     */
    void logout(String token);

    /**
     * 设置token时间
     *
     * @param token token
     * @param time  时间
     */
    void tokenTime(String token, long time);

    /**
     * 注册
     *
     * @param user 用户
     * @return
     */
    Resources register(User user);

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    Resources userName(String token);

    /**
     * 删除用户
     *
     * @param username
     */
    void delete(String username);

    /**
     * 修改密码
     * @param user
     * @param newPass
     * @return
     */
    Resources newPass(User user,String newPass);
}

