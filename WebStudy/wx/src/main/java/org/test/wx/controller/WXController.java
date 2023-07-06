package org.test.wx.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wx")
@Slf4j
public class WXController {
    @Autowired
    private WxMpService wxMpService;    //提供公众号服务

    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;    //储存公众号配置

    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;

    @PostMapping(value = "message", produces = "application/xml; charset=UTF-8")
    public String test(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce) throws IllegalAccessException {
        log.info("有消息发过来了");
        if (!wxMpService.checkSignature(timestamp, nonce, signature)){
            throw new IllegalAccessException("请求有问题");
        }
        WxMpXmlMessage wxMpXmlMessage=WxMpXmlMessage.fromXml(requestBody);
        WxMpXmlOutMessage wxMpXmlOutMessage;
        wxMpXmlOutMessage=wxMpMessageRouter.route(wxMpXmlMessage);
        return wxMpXmlOutMessage==null?"":wxMpXmlOutMessage.toXml();
    }

    @GetMapping("createMenu")
    public String createMenu() throws WxErrorException {
        WxMenu menu=new WxMenu();
        WxMenuButton buttonA=new WxMenuButton();
        buttonA.setType(WxConsts.MenuButtonType.CLICK);
        buttonA.setName("今日歌曲");
        buttonA.setKey("MUSIC1");
        WxMenuButton buttonB=new WxMenuButton();
        buttonB.setName("菜单");
        WxMenuButton buttonB1=new WxMenuButton();
        buttonB1.setType(WxConsts.MenuButtonType.VIEW);
        buttonB1.setName("搜索");
        buttonB1.setUrl("https://www.baidu.com/");
        WxMenuButton buttonB2 = new WxMenuButton();
        buttonB2.setType(WxConsts.MenuButtonType.VIEW);
        buttonB2.setName("视频");
        buttonB2.setUrl("https://v.qq.com/");
        WxMenuButton buttonB3 = new WxMenuButton();
        buttonB3.setType(WxConsts.MenuButtonType.CLICK);
        buttonB3.setName("赞一下我们");
        buttonB3.setKey("V1001_GOOD");
        buttonB.getSubButtons().add(buttonB1);
        buttonB.getSubButtons().add(buttonB2);
        buttonB.getSubButtons().add(buttonB3);
        menu.getButtons().add(buttonA);
        menu.getButtons().add(buttonB);
        return wxMpService.getMenuService().menuCreate(menu);
    }
}