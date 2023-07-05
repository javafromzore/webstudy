package org.test.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.test.car.convert.BrandConvert;
import org.test.car.dao.BrandMapper;
import org.test.car.model.vo.BrandVO;
import org.test.car.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BrandVO getBrand(long id) {
        brandMapper.getBrand(id);
        return BrandConvert.INSTANCE.convertVO(brandMapper.getBrand(id));
    }

    @Override
    public BrandVO listByCompanyId(long companyId) {
        return null;
    }
}