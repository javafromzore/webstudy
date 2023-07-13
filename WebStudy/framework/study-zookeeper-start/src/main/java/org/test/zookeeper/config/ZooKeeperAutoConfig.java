package org.test.zookeeper.config;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@AutoConfiguration
@EnableConfigurationProperties(ZooKeeperProperties.class)
public class ZooKeeperAutoConfig {
    @Autowired
    private ZooKeeperProperties zooKeeperProperties;

    @Bean
    public ZooKeeper zooKeeper() throws IOException {
        return new ZooKeeper(zooKeeperProperties.getAddress(), zooKeeperProperties.getTimeout(), watchedEvent -> {});
    }
}
