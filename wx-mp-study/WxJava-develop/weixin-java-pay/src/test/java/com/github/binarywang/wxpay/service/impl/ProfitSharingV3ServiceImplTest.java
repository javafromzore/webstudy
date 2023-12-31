package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试类
 *
 * @author yuanbo
 * @create 2022-04-26-22:33 PM
 */
@Test
@Slf4j
@Guice(modules = ApiTestModule.class)
public class ProfitSharingV3ServiceImplTest {
  @Inject
  private WxPayService payService;

  @Test
  public void testProfitSharingNotifyData() throws WxPayException {
    SignatureHeader header = new SignatureHeader();
    header.setSerialNo("Wechatpay-Serial");
    header.setTimeStamp("Wechatpay-Timestamp");
    header.setNonce("Wechatpay-Nonce");
    header.setSigned("Wechatpay-Signature");
    String data = "body";
    log.info(this.payService.getProfitSharingV3Service().getProfitSharingNotifyData(data,header).toString());
  }
}
