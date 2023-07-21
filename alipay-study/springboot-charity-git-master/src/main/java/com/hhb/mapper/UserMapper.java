package com.hhb.mapper;

import com.hhb.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //获取登陆的用户
    User getLoginUser(String account, String password);

    //修改个人信息
    int modifyUser(User user);

    //注册用户
    int registerUser(User user);
}
