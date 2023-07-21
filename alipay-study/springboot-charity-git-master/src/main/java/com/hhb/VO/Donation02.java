package com.hhb.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//对应 “参与募捐” 模块
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Donation02 {
    private int dona_id;
    private String dona_name;
    private String user_name;  //求助者姓名
    private String dona_detail;
    private int dona_state;
    private float dona_total;
    private float dona_current;
}
