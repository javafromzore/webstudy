package org.test.pay.controller;

import com.alipay.api.AlipayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.pay.service.PayService;

@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {
    @Autowired
    private PayService payService;

    @PostMapping("/test")
    public String test() throws Exception {
        log.info("统一收单下单并支付接口调用");
        return payService.tradeCreate();
    }
}
