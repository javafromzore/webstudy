package org.test.car.service;

import org.test.car.model.vo.CarVO;
import org.test.common.model.dto.IdDTO;

import java.util.List;

public interface CarService {
    CarVO getCar(long id);

    List<CarVO> listByIds(IdDTO idDTO);

    CarVO redisTest(long id);
}
