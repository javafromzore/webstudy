package org.test.wx.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.util.Map;

@Slf4j
public class TextHandler implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        log.info("TextHandler被调用");
        String content=wxMpXmlMessage.getContent();
        String outContent;
        if (content.contains("强风吹拂")){
            outContent="强风吹拂";
        }else if (content.contains("明日之诗")){
            outContent="明日之诗";
        }else {
            outContent=null;
        }
        return WxMpXmlOutMessage.TEXT()
                .content(outContent)
                .fromUser(wxMpXmlMessage.getToUser())
                .toUser(wxMpXmlMessage.getFromUser())
                .build();
    }
}
