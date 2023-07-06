package org.test.user.model.dom;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("collect_car")
public class CollectCarDO {
    private Long id;
    private Long carId;
    private Long collectId;
}
