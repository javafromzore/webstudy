package org.test.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.common.model.vo.Result;
import org.test.user.vo.collect.DetailVO;
import org.test.user.vo.user.UserVO;
import org.test.user.service.CollectService;

@RestController
@RequestMapping("/user/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @GetMapping("/getDetailByUserId")
    public Result<DetailVO> getDetailByUserId(@RequestParam("userId") long userId) {
        return Result.succeed(collectService.getDetailByUserId(userId));
    }
}