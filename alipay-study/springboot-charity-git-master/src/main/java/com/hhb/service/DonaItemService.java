package com.hhb.service;

import com.hhb.VO.dona_item;
import com.hhb.mapper.DonaItemMapper;
import com.hhb.pojo.Dona_item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonaItemService {
    @Autowired
    DonaItemMapper donaItemMapper;

    //添加一条dona_item
    public int addItem(Dona_item dona_item){
        return donaItemMapper.addItem(dona_item);
    }

    //查询我的贡献
    public List<dona_item> myItem(int user_id){
        return donaItemMapper.myItem(user_id);
    }
}
