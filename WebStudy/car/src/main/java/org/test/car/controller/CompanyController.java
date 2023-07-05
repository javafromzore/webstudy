package org.test.car.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.test.car.dao.CompanyMapper;
import org.test.car.model.vo.CompanyDetailVO;
import org.test.car.model.vo.CompanyVO;
import org.test.car.service.CompanyService;
import org.test.car.service.impl.CompanyServiceImpl;
import org.test.common.model.vo.Result;

@RestController
@RequestMapping("/car/company")
public class CompanyController {

    //构造器注入
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    //测试延迟加载
    @PostMapping("/getCompanyDetail")
    public Result<CompanyDetailVO> getCompanyDetail() {
        return Result.succeed(companyService.getCompanyDetail());
    }

    //测试二级缓存
    @GetMapping("/getCompany")
    public Result<CompanyVO> getCompany(@RequestParam long id) {
        return Result.succeed(companyService.getCompany(id));
    }
}
