package org.test.car.model.dom;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("brand")
public class BrandDO implements Serializable {
    private Long id;
    private String icon;
    private String name;
    private Long CompanyId;
}
