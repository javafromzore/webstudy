package com.hhb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dona_item {
    private int id;
    private int dona_id;
    private int user_id;    //捐款人id
    private float money;    //捐款数额
    private String time;
}
