package org.test.car.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.test.car.model.dom.CompanyDO;
import org.test.car.model.vo.CompanyVO;

@Mapper
public interface CompanyConvert {
    CompanyConvert INSTANCE = Mappers.getMapper(CompanyConvert.class);

    CompanyVO convertVO(CompanyDO companyDO);
}
