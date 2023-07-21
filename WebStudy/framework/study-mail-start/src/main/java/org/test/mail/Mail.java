package org.test.mail;

import lombok.Getter;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Getter
public class Mail {
    public static JavaMailSenderImpl javaMailSender;
    static {
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");//链接服务器
        //javaMailSender.setPort(25);//默认使用25端口发送
        javaMailSender.setUsername("2917462604@qq.com");//账号
        javaMailSender.setPassword("wgmefugkwypodedc");//授权码
        javaMailSender.setDefaultEncoding("UTF-8");

        Properties properties = new Properties();
        //properties.setProperty("mail.debug", "true");//启用调试
        //properties.setProperty("mail.smtp.timeout", "1000");//设置链接超时
        //设置通过ssl协议使用465端口发送、使用默认端口（25）时下面三行不需要
        properties.setProperty("mail.smtp.auth", "true");//开启认证
        properties.setProperty("mail.smtp.socketFactory.port", "465");//设置ssl端口
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        javaMailSender.setJavaMailProperties(properties);
    }
}
