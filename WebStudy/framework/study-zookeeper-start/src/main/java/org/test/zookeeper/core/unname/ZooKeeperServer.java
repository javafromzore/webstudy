package org.test.zookeeper.core.unname;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ZooKeeperServer implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("-----------------------------------------");
        System.out.println("connect state:" + watchedEvent.getState());
        System.out.println("event type:" + watchedEvent.getType());
        System.out.println("znode path:" + watchedEvent.getPath());
        System.out.println("-----------------------------------------");
    }
}
