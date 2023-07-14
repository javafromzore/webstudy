package org.test.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectCarMapper {
    long[] listCarIdByCollectId(@Param("collectId") long collectId);
}
