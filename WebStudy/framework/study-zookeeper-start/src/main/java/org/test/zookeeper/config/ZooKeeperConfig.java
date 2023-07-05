package org.test.zookeeper.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(ZooKeeperProperties.class)
public class ZooKeeperConfig {

}
