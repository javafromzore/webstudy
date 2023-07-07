package org.test.car.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.car.model.dom.CarDO;
import org.test.common.model.dto.IdDTO;

import java.util.List;

@Mapper
public interface CarMapper {
    CarDO getCar(@Param("id") long id);

    List<CarDO> listByIds(IdDTO idDTO);
}
