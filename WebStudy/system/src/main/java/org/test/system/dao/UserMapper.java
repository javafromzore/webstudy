package org.test.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.system.pojo.model.UserDO;

@Mapper
public interface UserMapper {
    UserDO getByAccountAndPassword(@Param("account") String account, @Param("password") String password);
}
