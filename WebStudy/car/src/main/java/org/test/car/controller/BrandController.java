package org.test.car.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.car.model.vo.BrandVO;
import org.test.car.service.BrandService;
import org.test.common.model.vo.Result;

@RestController
@RequestMapping("/car/brand")
@Slf4j  //简化日志使用
public class BrandController {
    //变量注入，就是直接声明一个变量
    @Autowired
    private BrandService brandService;

    //测试二级缓存
    @GetMapping("/getBrand")
    public Result<BrandVO> getBrand(@RequestParam("id") long id) {
        return Result.succeed(brandService.getBrand(id));
    }


    @GetMapping("/listByCompanyId")
    public Result<BrandVO> listByCompanyId(@RequestParam("companyId") long companyId) {
        return Result.succeed(brandService.listByCompanyId(companyId));
    }
}