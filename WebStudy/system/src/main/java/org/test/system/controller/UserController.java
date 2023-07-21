package org.test.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.common.model.vo.Result;
import org.test.system.pojo.dto.LoginDTO;
import org.test.system.pojo.vo.TokenVO;
import org.test.system.service.UserService;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PermitAll
    @PostMapping("/login")
    public Result<TokenVO> login(LoginDTO dto) {
        return Result.succeed(userService.login(dto));
    }
}