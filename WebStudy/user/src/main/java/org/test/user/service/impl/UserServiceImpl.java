package org.test.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.user.convert.UserConvert;
import org.test.user.dao.UserMapper;
import org.test.user.vo.user.UserVO;
import org.test.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO getUser(long id) {
        return UserConvert.INSTANCE.convertVO(userMapper.getUser(id));
    }
}