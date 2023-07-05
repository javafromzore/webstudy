package org.test.car.model.vo;

import lombok.Data;
import org.test.car.model.dom.BrandDO;

import java.util.List;

@Data
public class CompanyDetailVO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String icon;
    private List<BrandVO> brandDOList;
}
