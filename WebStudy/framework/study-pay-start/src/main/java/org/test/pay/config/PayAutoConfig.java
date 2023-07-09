package org.test.pay.config;


import com.alipay.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.pay.properity.PayProperity;

@Configuration
@EnableConfigurationProperties(PayProperity.class)
public class PayAutoConfig {
    @Autowired
    private PayProperity payProperity;

    @Bean
    public AlipayClient alipayClient() throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl(payProperity.getGatewayUrl());
        alipayConfig.setAppId(payProperity.getAppId());
        alipayConfig.setPrivateKey(payProperity.getSellerId());
        alipayConfig.setFormat(AlipayConstants.FORMAT_JSON);
        alipayConfig.setCharset(AlipayConstants.CHARSET_UTF8);
        alipayConfig.setAlipayPublicCertContent(payProperity.getAlipayPublicKey());
        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
        alipayConfig.setAlipayPublicCertContent(payProperity.getAlipayPublicKey());
        return new DefaultAlipayClient(alipayConfig);
    }
}
