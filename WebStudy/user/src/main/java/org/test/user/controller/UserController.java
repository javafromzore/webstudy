package org.test.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.user.vo.user.UserVO;
import org.test.user.service.UserService;
import org.test.common.model.vo.Result;

import java.io.Serializable;

@RestController
@RequestMapping("/user/user")
public class UserController implements Serializable{
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;


    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public Result<UserVO> getUser(@RequestParam("id") long id) {
        return Result.succeed(userService.getUser(id));
    }

    @GetMapping("/redisTest")
    public Result<String> redisTest() {
        template.opsForValue().set("people", "jack");
        return Result.succeed((String) template.opsForValue().get("people"));
    }
}