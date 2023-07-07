package org.test.car.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.test.car.model.dom.CarDO;
import org.test.car.model.vo.CarVO;

import java.util.List;

@Mapper
public interface CarConvert {
    CarConvert INSTANCE= Mappers.getMapper(CarConvert.class);
    CarVO convertVO(CarDO brandDO);

    List<CarVO> convertVOList(List<CarDO> carDOS);
}
