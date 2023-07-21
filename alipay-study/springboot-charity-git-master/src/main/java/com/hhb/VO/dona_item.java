package com.hhb.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//对应 “我的贡献” 模块
@Data
@AllArgsConstructor
@NoArgsConstructor
public class dona_item {
    private int id;
    private int dona_id;
    private String dona_name;   //项目名称
    private int user_id;    //捐款人id
    private String user_name;   //捐款人姓名
    private float money;    //捐款数额
    private String time;
}
