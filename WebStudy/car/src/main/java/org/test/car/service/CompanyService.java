package org.test.car.service;

import org.test.car.model.vo.CompanyDetailVO;
import org.test.car.model.vo.CompanyVO;

public interface CompanyService {
    CompanyDetailVO getCompanyDetail();

    CompanyVO getCompany(long id);
}
