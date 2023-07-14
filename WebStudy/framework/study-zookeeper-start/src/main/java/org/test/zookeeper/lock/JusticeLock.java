package org.test.zookeeper.lock;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JusticeLock {
    private static final String LOCK_ROOT_NODE="/JLock";   //非公平锁的根节点路径
    private static final String LOCK_CHILD_NODE="Lock_";    //当前锁的子节点路径
    private String lockPath;    //当前锁的路径
    private ZooKeeper client;   //每一次使用锁的会话（每次上锁解锁的会话）
    private final Watcher watcher=watchedEvent -> {
        synchronized (this){
            this.notifyAll();
        }
    };  //节点被删除后的监听器

    public JusticeLock() throws IOException {
        this.client=new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 10000000,
                watchedEvent -> {
                    System.out.println("任何节点的改变都会引起该回调方法");
                });
    }

    //上锁
    public void lock() throws InterruptedException, KeeperException {
        this.createNode();
        this.addLock();
    }

    private void createNode() throws InterruptedException, KeeperException {
        if (client.exists(JusticeLock.LOCK_ROOT_NODE, true)==null){
            client.create(JusticeLock.LOCK_ROOT_NODE, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        List<String> childNodes=client.getChildren(JusticeLock.LOCK_ROOT_NODE, false);
        this.lockPath=client.create(JusticeLock.LOCK_ROOT_NODE+"/"+ JusticeLock.LOCK_CHILD_NODE, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        childNodes=client.getChildren(JusticeLock.LOCK_ROOT_NODE, false);
        client.exists(JusticeLock.LOCK_ROOT_NODE+"/"+ JusticeLock.LOCK_CHILD_NODE, watcher);
        childNodes=client.getChildren(JusticeLock.LOCK_ROOT_NODE, false);
    }

    private void addLock() throws InterruptedException, KeeperException {
        List<String> childNodes=client.getChildren(JusticeLock.LOCK_ROOT_NODE, false);
        Collections.sort(childNodes);
        if (childNodes.indexOf(lockPath.substring(JusticeLock.LOCK_ROOT_NODE.length()+1))==0){
            return;
        }
        synchronized (watcher){
            watcher.wait(1000);
        }
        addLock();
    }

    //解锁
    public void unLock() throws InterruptedException {
        client.close();
    }
}