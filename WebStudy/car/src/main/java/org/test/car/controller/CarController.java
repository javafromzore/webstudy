package org.test.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.test.car.model.vo.CarVO;
import org.test.car.service.CarService;
import org.test.common.model.dto.IdDTO;
import org.test.common.model.vo.Result;

import java.util.List;

@RestController
@RequestMapping("/car/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/getCar")
    public Result<CarVO> getCar(@RequestParam("id") long id) {
        return Result.succeed(carService.getCar(id));
    }

    @PostMapping("/listByIds")
    public Result<List<CarVO>> listByIds(@RequestBody IdDTO idDTO) {
        return Result.succeed(carService.listByIds(idDTO));
    }
}