package org.test.pay.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.pay.properity.PayProperity;
import org.test.pay.service.PayService;

@Service
@Slf4j
public class PaySerivceImpl implements PayService {
    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private PayProperity payProperity;

    @Override
    public String tradeCreate() throws Exception {
        AlipayTradePagePayRequest request=new AlipayTradePagePayRequest();
        request.setReturnUrl(payProperity.getAlipayReturnUrl());
        AlipayTradePagePayModel model=new AlipayTradePagePayModel();
        model.setOutTradeNo("001");
        model.setTotalAmount("100");
        model.setSubject("车");
        model.setProductCode("10016");
        request.setBizModel(model);
        AlipayTradePagePayResponse response=alipayClient.pageExecute(request);
        if (!response.isSuccess()){
            log.info("调用失败");
            return null;
        }
        log.info("调用成功:"+response.getBody());
        return null;
    }
}