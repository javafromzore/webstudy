package org.test.car.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.car.model.dom.CompanyDO;
import org.test.car.model.vo.CompanyDetailVO;

@Mapper
public interface CompanyMapper {
    CompanyDetailVO getCompanyDetail(@Param("id") long id);

    CompanyDO getCompany(@Param("id") long id);
}
