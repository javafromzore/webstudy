package com.hhb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int user_id;
    private String user_name;
    private String user_account;
    private String user_password;
    private String user_img;
    private int user_type;
    private int user_gender;
    private int user_age;
    private String user_phone;
    private float user_donate;
}
