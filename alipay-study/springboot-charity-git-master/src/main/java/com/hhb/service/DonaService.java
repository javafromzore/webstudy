package com.hhb.service;

import com.hhb.mapper.DonationMapper;
import com.hhb.pojo.Donation;
import com.hhb.VO.Donation02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonaService {
    @Autowired
    DonationMapper donationMapper;

    //添加一条donation信息
    public int addDonation(Donation dona){
        return donationMapper.addDonation(dona);
    }

    //通过 user_id 查询发起的募捐
    public List<Donation> queryById(int user_id){
        return donationMapper.queryById(user_id);
    }


    //查询所有募捐信息，多表查询
    // donation02，donation02中user_name字段与donation不同
    public List<Donation02> queryState_1(){
        return donationMapper.queryState_1();
    }

    //通过dona_id查询donation
    public Donation queryByDonaid(int dona_id){
        return donationMapper.queryByDonaid(dona_id);
    }

    //根据 dona_id 修改更新 donation表中内容
    public int updateById(Donation donation){
        return donationMapper.updateById(donation);
    }
}
