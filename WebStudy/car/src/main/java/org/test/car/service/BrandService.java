package org.test.car.service;

import org.test.car.model.vo.BrandVO;

public interface BrandService {
    public BrandVO getBrand(long id);

    BrandVO listByCompanyId(long companyId);
}
