package org.test.school.model.test;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("student")
@Data
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private Double GPA;
    private Class aClass;
}
