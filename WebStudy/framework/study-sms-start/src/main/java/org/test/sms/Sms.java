package org.test.sms;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.List;

public class Sms {
    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";
    static final String accessKeyId = "LTAI5tEUFMfbwxWXGmeDGGiq";
    static final String accessKeySecret = "yASxk4YMsEFyr49fQyUoJ7lQuCfBjG";
    //调用阿里云接口对短信服务进行处理
    public static SendSmsResponse sendSms(String tel, String code, String templateCode, String signName) throws ClientException {
        //初始化acsClient
        IClientProfile profile =DefaultProfile .getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //请求部分的组装
        SendSmsRequest request = new SendSmsRequest();
        //接收验证码的电话号码
        request.setPhoneNumbers(tel);
        //短信签名，必须在审核通过后再能使用
        request.setSignName(signName);
        //使用你在阿里云平台申请的“模板管理”中的短信模板名称
        request.setTemplateCode(templateCode);
        //“\”代表转译
        //模版内容:您的动态码为：${code}，您正在进行密码重置操作，如非本人操作，请忽略本短信
        request.setTemplateParam("{\"code\":"+code+"}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }
}
