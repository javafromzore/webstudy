package com.hhb.mapper;


import com.hhb.pojo.User_category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserCateMapper {

    //通过id 查询类别对象
    User_category getCateById(int id);
}
