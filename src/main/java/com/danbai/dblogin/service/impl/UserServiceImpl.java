package com.danbai.dblogin.service.impl;

import com.danbai.dblogin.entity.Resources;
import com.danbai.dblogin.entity.User;
import com.danbai.dblogin.mapper.UserMapper;
import com.danbai.dblogin.service.UserService;
import com.danbai.dblogin.utils.StringUtils;
import com.danbai.dblogin.utils.uuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * @author danbai
 * @date 2020-02-04 16:52
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Resources login(User user) {
        User user1 = getUser(user.getUsername());
        //用户名存在
        if (user1 != null) {
            if (StringUtils.md5(user.getPassword()).equals(user1.getPassword())) {
                String uuid = uuidUtils.getUUID32();
                redisTemplate.opsForValue().set(uuid, user.getUsername(), 1, TimeUnit.DAYS);
                return Resources.Ok().setMsg("登陆成功").setData(uuid);
            }
            return Resources.Err().setMsg("账号或密码错误");
        }
        return Resources.Err().setMsg("账号不存在");
    }

    @Override
    public User getUser(String username) {
        return userMapper.selectOne(new User(username, null));
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    @Override
    public void tokenTime(String token, long time) {
        redisTemplate.expire(token, time, TimeUnit.SECONDS);
    }

    @Override
    public Resources register(User user) {
        User user1 = getUser(user.getUsername());
        //用户名存在
        if (user1 == null) {
            user.setPassword(StringUtils.md5(user.getPassword()));
            userMapper.insert(user);
            return Resources.Ok().setMsg("注册成功");
        }
        return Resources.Err().setMsg("账号已存在");
    }

    @Override
    public Resources userName(String token) {
        String username = (String) redisTemplate.opsForValue().get(token);
        if (!StringUtils.hasLength(username)) {
            return Resources.Err().setMsg("Token不存在");
        }
        return Resources.Ok().setData(username);
    }

    @Override
    public void delete(String username) {
        userMapper.delete(new User(username,null));
    }
}
