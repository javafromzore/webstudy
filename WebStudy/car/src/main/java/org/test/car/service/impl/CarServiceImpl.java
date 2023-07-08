package org.test.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.test.car.convert.CarConvert;
import org.test.car.dao.CarMapper;
import org.test.car.model.vo.CarVO;
import org.test.car.service.CarService;
import org.test.common.model.dto.IdDTO;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    @Override
    public CarVO getCar(long id) {
        return CarConvert.INSTANCE.convertVO(carMapper.getCar(id));
    }

    @Override
    public List<CarVO> listByIds(IdDTO idDTO) {
        return CarConvert.INSTANCE.convertVOList(carMapper.listByIds(idDTO));
    }

    @Override
    public CarVO redisTest(long id) {
//        template.
        return null;
    }
}