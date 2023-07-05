package org.test.car.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.car.model.dom.BrandDO;
import org.test.car.model.vo.BrandVO;

import java.util.List;

@Mapper
public interface BrandMapper {
    List<BrandVO> listByCompanyId(@Param("companyId") long companyId);

    BrandDO getBrand(@Param("id") long id);
}
