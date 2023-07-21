package com.hhb.mapper;


import com.hhb.pojo.Donation;
import com.hhb.VO.Donation02;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DonationMapper {
    //添加一条donation信息
    int addDonation(Donation dona);

    //通过 user_id 查询发起的募捐
    List<Donation> queryById(int user_id);

    //查询所有募捐信息，多表查询
    // donation02，donation02中user_name字段与donation不同
    List<Donation02> queryState_1();

    //通过dona_id查询donation
    Donation queryByDonaid(int dona_id);

    //根据 dona_id 修改更新 donation表中内容
    int updateById(Donation donation);
}
