package org.test.car.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.test.car.model.dom.BrandDO;
import org.test.car.model.vo.BrandVO;

@Mapper
public interface BrandConvert {
    BrandConvert INSTANCE= Mappers.getMapper(BrandConvert.class);
    BrandVO convertVO(BrandDO brandDO);
}
