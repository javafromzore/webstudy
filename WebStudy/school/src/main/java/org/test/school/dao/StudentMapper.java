package org.test.school.dao;

import org.apache.ibatis.annotations.Mapper;
import org.test.school.model.test.Student;

@Mapper
public interface StudentMapper {
    Student findById(Integer id);
}
