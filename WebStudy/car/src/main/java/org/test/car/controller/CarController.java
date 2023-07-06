package org.test.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.car.model.vo.CarVO;
import org.test.car.service.CarService;
import org.test.common.model.vo.Result;

@RestController
@RequestMapping("/car/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/getCar")
    public Result<CarVO> getCar(@RequestParam("id") long id) {
        return Result.succeed(carService.getCar(id));
    }
}