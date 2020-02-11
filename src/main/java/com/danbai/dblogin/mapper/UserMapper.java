package com.danbai.dblogin.mapper;

import com.danbai.dblogin.entity.User;
import com.danbai.dblogin.utils.MyMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sun.misc.Contended;

/**
 * @author Administrator
 */

@Repository
public interface UserMapper extends MyMapper<User> {
}
