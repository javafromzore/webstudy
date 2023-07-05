package org.test.school.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassMapper {
    Class findByClassId(Integer id);
}
