package org.test.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.user.model.dom.UserDO;

@Mapper
public interface UserMapper {
    UserDO getUser(@Param("id") long id);
}
