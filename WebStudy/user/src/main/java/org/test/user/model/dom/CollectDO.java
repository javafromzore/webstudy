package org.test.user.model.dom;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("collect")
public class CollectDO {
    private Long id;
    private Long userId;
}
