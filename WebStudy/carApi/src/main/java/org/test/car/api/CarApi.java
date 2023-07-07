package org.test.car.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.test.car.vo.CarVO;
import org.test.common.model.dto.IdDTO;
import org.test.common.model.vo.Result;

import java.util.List;

@FeignClient(name = "car", path = "car/car")
public interface CarApi {
    @PostMapping("/listByIds")
    Result<List<CarVO>> listByIds(@RequestBody IdDTO idDTO);
}
