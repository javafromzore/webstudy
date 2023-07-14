package org.test.zookeeper.lock;

import org.apache.zookeeper.*;

import java.io.IOException;

public class UnJusticeLock {
    private static final String LOCK_NODE="/UJLock";
    private ZooKeeper client;

    private final Watcher watcher= watchedEvent -> {
        synchronized (this) {
            this.notifyAll();
        }
    };

    public UnJusticeLock() throws IOException {
        this.client = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 10000000,
                watchedEvent -> {
                    if (watchedEvent.getState()== Watcher.Event.KeeperState.Disconnected){
                        System.out.println("断开连接");
                    }
                });
    }

    public void lock() throws InterruptedException, KeeperException {
        if (client.exists(UnJusticeLock.LOCK_NODE, false)==null){
            client.create(UnJusticeLock.LOCK_NODE, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            client.exists(UnJusticeLock.LOCK_NODE, this.watcher);
            return;
        }
        synchronized (this) {
            this.wait(1000);
        }
    }

    public void unLock() throws InterruptedException {
        this.client.close();
    }
}