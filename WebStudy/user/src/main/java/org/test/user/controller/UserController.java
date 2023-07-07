package org.test.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.user.vo.user.UserVO;
import org.test.user.service.UserService;
import org.test.common.model.vo.Result;

@RestController
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public Result<UserVO> getUser(@RequestParam("id") long id) {
        return Result.succeed(userService.getUser(id));
    }
}