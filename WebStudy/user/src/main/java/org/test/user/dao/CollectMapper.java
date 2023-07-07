package org.test.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.test.user.model.CollectDO;

@Mapper
public interface CollectMapper {
    CollectDO getByUserId(@Param("userId") long userId);
}
