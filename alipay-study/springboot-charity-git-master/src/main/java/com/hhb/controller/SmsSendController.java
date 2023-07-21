package com.hhb.controller;

import com.hhb.component.SmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

//返回json数据
@RestController
public class SmsSendController {

    @Autowired
    SmsComponent smsComponent;

    //获取短信验证码的方法
    @GetMapping("/sms/sendcode")
    public void sendCode(@RequestParam("mobile") String mobile, HttpSession session){
        System.out.println(mobile);

        //生成验证码
        String code = UUID.randomUUID().toString().substring(0,5);
        System.out.println(code);
        //验证码的再次校验(放在session中)
        session.setAttribute("code",code);

        smsComponent.sendSmsCode(mobile,code);
    }
}
