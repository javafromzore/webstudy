package org.test.zookeeper.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = "web-study.zookeeper.configuration-properties")
@Data
public class ZooKeeperProperties {
    private String serverUrl;
    private int sessionTimeOut;
}
