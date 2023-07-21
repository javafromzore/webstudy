package com.hhb.mapper;

import com.hhb.VO.dona_item;
import com.hhb.pojo.Dona_item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DonaItemMapper {
    //添加一条dona_item
    int addItem(Dona_item dona_item);

    //查询我的贡献
    List<dona_item> myItem(int user_id);

    //查询所有捐款条目，并且有三表查询 查询出项目名称和捐款人姓名
    List<dona_item> allItem();
}
