package org.test.zookeeper.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.zookeeper")
@Data
public class ZooKeeperProperties {
    private String address;
    private int timeout;
}