package org.test.car.model.dom;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("car_company")
public class CompanyDO implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String icon;
}
