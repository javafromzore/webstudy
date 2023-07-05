package org.test.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.car.convert.CompanyConvert;
import org.test.car.dao.CompanyMapper;
import org.test.car.model.vo.CompanyDetailVO;
import org.test.car.model.vo.CompanyVO;
import org.test.car.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
    //setter注入
    private CompanyMapper companyMapper;

    @Autowired
    public void setCompanyMapper(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public CompanyDetailVO getCompanyDetail() {
        CompanyDetailVO companyDetailVO = companyMapper.getCompanyDetail(1);
        companyDetailVO.getBrandDOList().get(0).getCompanyId();
        return companyDetailVO;
    }

    @Override
    public CompanyVO getCompany(long id) {
        return CompanyConvert.INSTANCE.convertVO(companyMapper.getCompany(id));
    }
}