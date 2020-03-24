package cn.p00q.dblogin.service.impl;

import cn.p00q.dblogin.entity.Resources;
import cn.p00q.dblogin.entity.User;
import cn.p00q.dblogin.mapper.UserMapper;
import cn.p00q.dblogin.service.UserService;
import cn.p00q.dblogin.utils.StringUtils;
import cn.p00q.dblogin.utils.uuidUtils;
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
        //用户是否存在
        if (user1 != null) {
            if (StringUtils.md5(user.getPassword()).equals(user1.getPassword())) {

                String token;
                if (redisTemplate.hasKey(user.getUsername())) {
                    token = (String) redisTemplate.opsForValue().get(user.getUsername());
                    Resources.Ok().setData(token);
                }else {
                    token = uuidUtils.getUUID32();
                    redisTemplate.opsForValue().set(user.getUsername(), token, 1, TimeUnit.DAYS);
                    redisTemplate.opsForValue().set(token, user.getUsername(), 1, TimeUnit.DAYS);
                }
                return Resources.Ok().setMsg("登陆成功").setData(token);
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
        redisTemplate.delete(userName(token).getData());
        redisTemplate.delete(token);
    }

    @Override
    public void tokenTime(String token, long time) {
        redisTemplate.expire(userName(token).getData(), time, TimeUnit.SECONDS);
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
        if (userMapper.delete(new User(username, null)) > 0) {
            if (redisTemplate.hasKey(username)) {
                redisTemplate.delete(redisTemplate.opsForValue().get(username));
                redisTemplate.delete(username);
            }
        }
    }

    @Override
    public Resources newPass(User user, String newPass) {
        user.setPassword(StringUtils.md5(user.getPassword()));
        User user1 = userMapper.selectOne(user);
        if (user1 != null) {
            user1.setPassword(StringUtils.md5(newPass));
            if (userMapper.updateByPrimaryKey(user1) > 0) {
                logout(user1.getUsername());
                return Resources.Ok();
            }
        }
        return Resources.Err().setMsg("账号密码错误");
    }
}
