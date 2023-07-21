package com.hhb.service;

import com.hhb.mapper.UserMapper;
import com.hhb.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    //获取登陆的用户
    public User getLoginUser(String account, String password){
        return userMapper.getLoginUser(account,password);
    }

    //修改个人信息
    public int modifyUser(User user){
        return userMapper.modifyUser(user);
    }

    //注册用户
    public int registerUser(User user){
        return userMapper.registerUser(user);
    }
}
