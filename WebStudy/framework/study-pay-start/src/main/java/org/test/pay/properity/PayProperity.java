package org.test.pay.properity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "alipay")
@Data
public class PayProperity {
    private String appId;
    private String sellerId;
    private String gatewayUrl;
    private String merchantPrivateKey;
    private String alipayPublicKey;
    private String alipayReturnUrl;
}
