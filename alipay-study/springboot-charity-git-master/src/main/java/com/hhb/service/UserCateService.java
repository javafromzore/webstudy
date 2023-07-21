package com.hhb.service;

import com.hhb.mapper.UserCateMapper;
import com.hhb.pojo.User_category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCateService {

    @Autowired
    UserCateMapper userCateMapper;

    //通过id 查询类别对象
    public User_category getCateById(int id){
        return userCateMapper.getCateById(id);
    }
}
