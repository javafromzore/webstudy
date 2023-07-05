package org.test.school.model.test;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.lang.annotation.Target;
import java.util.List;

@TableName("school")
@Data
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School {
    private Integer id;
    private String name;
    private List<Student> students;
}
