package org.test.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.car.convert.CarConvert;
import org.test.car.dao.CarMapper;
import org.test.car.model.vo.CarVO;
import org.test.car.service.CarService;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper brandMapper;

    @Override
    public CarVO getCar(long id) {
        return CarConvert.INSTANCE.convertVO(brandMapper.getCar(id));
    }
}