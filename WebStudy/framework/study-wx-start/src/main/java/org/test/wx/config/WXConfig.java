package org.test.wx.config;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.wx.handler.ImageHandler;
import org.test.wx.handler.SubscribeHandler;
import org.test.wx.handler.TextHandler;
import org.test.wx.handler.UnSubscribeHandler;

@Configuration
public class WXConfig {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private TextHandler textHandler;

    @Autowired
    private ImageHandler imageHandler;

    @Autowired
    private SubscribeHandler subscribeHandler;

    @Autowired
    private UnSubscribeHandler unSubscribeHandler;


    @Bean
    public WxMpMessageRouter wxMpMessageRouter(){
        final WxMpMessageRouter router=new WxMpMessageRouter(wxMpService);
        router.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT).handler(textHandler).end();
        router.rule().async(false).msgType(WxConsts.XmlMsgType.IMAGE).handler(imageHandler).end();
        router.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.SUBSCRIBE).handler(subscribeHandler).end();
        router.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.UNSUBSCRIBE).handler(unSubscribeHandler).end();
        return router;
    }
}