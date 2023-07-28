package org.test.car.controller;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.web.bind.annotation.*;
import org.test.aop.AopAnnotation;
import org.test.aop.Aspect;
import org.test.car.model.vo.CarVO;
import org.test.car.service.CarService;
import org.test.common.model.dto.IdDTO;
import org.test.common.model.vo.Result;
import org.test.userapi.api.UserApi;
import org.test.userapi.api.UserApi2;

import java.util.List;

@RestController
@RequestMapping("/car/car")
@RefreshScope //当配置中心配置更改时，进行刷新
public class CarController {
    @Value("${user.id}")
    private String userId;

    @Value("${user.age}")
    private String userAge;

    @Value("${user.name}")
    private String userName;

    @Autowired
    private CarService carService;

    @Autowired
    private UserApi userApi;

    @Autowired
    private UserApi2 userApi2;

    @GetMapping("/getCar")
    public Result<CarVO> getCar(@RequestParam("id") long id) {
        return Result.succeed(carService.getCar(id));
    }

    @PostMapping("/listByIds")
    public Result<List<CarVO>> listByIds(@RequestBody IdDTO idDTO) {
        return Result.succeed(carService.listByIds(idDTO));
    }

    @PostMapping("/redisTest")
    public Result<CarVO> redisTest(@RequestParam("id") long id) {
        return Result.succeed(carService.redisTest(id));
    }

    @GetMapping("/concurrentSafeTest")
    public Result concurrentSafeTest() {
        for (int i=0; i<5000; i++){
            userApi.ticketSeller();
            userApi2.ticketSeller();
        }
        return Result.succeed();
    }

    @AopAnnotation
    @PostMapping("/aopTest")
    public Result aopTest(@ModelAttribute("testKey") String testValue) {
        System.out.println(testValue);
        throw new RuntimeException();
//        return Result.succeed();
    }

    @PostMapping("/nacosConfigTest")
    public Result nacosConfigTest() {
        return Result.succeed(userId+"; "+userName+"; "+userAge);
    }
}