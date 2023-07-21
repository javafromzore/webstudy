package com.hhb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    private int dona_id;
    private String dona_name;
    private int user_id;
    private String dona_detail;
    private int dona_state;
    private float dona_total;
    private float dona_current;
}
