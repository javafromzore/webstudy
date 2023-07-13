package org.test.zookeeper.config;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.test.zookeeper.lock.LockSample;

import java.io.IOException;

@AutoConfiguration
@EnableConfigurationProperties({ZooKeeperProperties.class})
public class ZooKeeperAutoConfig {
    @Autowired
    private ZooKeeperProperties properties;

    @Bean(name = "client")
    //prototype 每次注入bean都会创建一个新的实例
//    @Scope("prototype")
    public ZooKeeper zooKeeper() throws IOException {
        return new ZooKeeper(properties.getAddress(), properties.getTimeout(), watchedEvent -> {
            if (watchedEvent.getState()== Watcher.Event.KeeperState.Disconnected){
                System.out.println("失去连接");
            }
        });
    }
}