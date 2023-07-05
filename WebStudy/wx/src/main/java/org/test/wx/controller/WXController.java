package org.test.wx.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class WXController {
    @Autowired
    private WxMpService wxMpService;    //提供公众号服务

    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;    //储存公众号配置

    @GetMapping("/test")
    public String test(String signature, String timestamp, String nonce,String echostr){
        if (wxMpService.checkSignature(timestamp, nonce, signature)){
            return echostr;
        }
        return null;
    }
}
