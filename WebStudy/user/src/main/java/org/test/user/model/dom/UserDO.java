package org.test.user.model.dom;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("brand")
public class UserDO {
    private Long id;
    private String name;
}
