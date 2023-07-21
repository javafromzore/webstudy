package org.test.system.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.test.system.dao.UserMapper;
import org.test.system.pojo.dto.LoginDTO;
import org.test.system.pojo.model.UserDO;
import org.test.system.pojo.vo.TokenVO;
import org.test.system.service.UserService;

import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public TokenVO login(LoginDTO dto) {
        UserDO userDO=userMapper.getByAccountAndPassword(dto.getAccount(), dto.getPassword());
        if (userDO==null){
            throw new RuntimeException();
        }
        String token=UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, JSON.toJSONString(userDO));
        TokenVO tokenVO=new TokenVO();
        tokenVO.setToken(token);
        return tokenVO;
    }
}