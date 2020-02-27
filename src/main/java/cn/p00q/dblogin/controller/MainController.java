package cn.p00q.dblogin.controller;


import cn.p00q.dblogin.entity.Resources;
import cn.p00q.dblogin.entity.User;
import cn.p00q.dblogin.service.impl.UserServiceImpl;
import cn.p00q.dblogin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danbai
 * @date 2020-01-24 19:35
 */
@RestController
public class MainController {
    @Autowired
    UserServiceImpl userService;
    @RequestMapping("/login")
    public Resources login(User user) {
        if (StringUtils.hasLength(user.getUsername()) & StringUtils.hasLength(user.getPassword())) {
            return userService.login(user);
        }
        return Resources.Err().setMsg(User.NOTKONG);
    }

    @RequestMapping("/logout")
    public Resources logout(String token) {
        if (StringUtils.hasLength(token)) {
            userService.logout(token);
            return Resources.Ok();
        }
        return Resources.Err().setMsg("token有误");
    }

    @RequestMapping("/tokenTime")
    public Resources tokenTime(String token, long time) {
        if (StringUtils.hasLength(token)) {
            userService.tokenTime(token, time);
            return Resources.Ok();
        }
        return Resources.Err().setMsg("token有误");
    }

    @RequestMapping("/register")
    public Resources register(User user) {
        if (StringUtils.hasLength(user.getUsername()) & StringUtils.hasLength(user.getPassword())) {
            return userService.register(user);
        }
        return Resources.Err().setMsg(User.NOTKONG);
    }

    @RequestMapping("/username")
    public Resources userName(String token) {
        if (StringUtils.hasLength(token)) {
            return userService.userName(token);
        }
        return Resources.Err().setMsg("token有误");
    }

    @RequestMapping("/delete")
    public Resources deleteUser(String username) {
        if (StringUtils.hasLength(username)) {
            userService.delete(username);
        }
        return Resources.Ok();
    }
    @RequestMapping("/user")
    public Resources user(String username) {
        if (StringUtils.hasLength(username)) {
            User user = userService.getUser(username);
            if(user==null){
                return Resources.Ok().setData(false);
            }else {
                return Resources.Ok().setData(true);
            }
        }
        return Resources.Ok();
    }
}
