package org.test.zookeeper.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = "web-study.zookeeper")
@Data
public class ZooKeeperProperties {
    private String address;
    private int timeout;
}