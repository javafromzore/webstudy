package org.test.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.test.zookeeper.config.ZooKeeperProperties;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LockSample {
    @Autowired
    private ZooKeeperProperties properties;

    private ZooKeeper client;
    private static final String LOCK_ROOT_PATH="/Locks";
    private static final String LOCK_NODE_NAME="Lock_";
    //当前线程的锁路径，如果解锁或者会话失效，则删除节点
    private String lockPath;

    private Watcher watcher=new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println(watchedEvent.getPath()+"锁释放");
            //todo 这里为什么要加锁？
            synchronized (this) {
                notifyAll();
            }
        }
    };

    public LockSample() throws IOException {
        client=new ZooKeeper(properties.getAddress(), properties.getTimeout(),
                //todo 既然后续可以自定义监视器，那么这一个监视器的作用是什么？
                watchedEvent -> {
            if (watchedEvent.getState()== Watcher.Event.KeeperState.Disconnected){
                System.out.println("失去连接");
            }
        });
    }

    public void acquireLock() throws InterruptedException, KeeperException {
        createLock();
        attemptLock();
    }

    private void createLock() throws InterruptedException, KeeperException {
        //如果没有分布式锁根节点，则创建
        if (client.exists(LOCK_ROOT_PATH, false)==null){
            client.create(LOCK_ROOT_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        //创建该线程对应的节点
        String lockPath=client.create(LOCK_ROOT_PATH+"/"+LOCK_NODE_NAME, Thread.currentThread().getName().getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(Thread.currentThread().getName()+"锁创建"+lockPath);
        this.lockPath=lockPath;
    }

    private void attemptLock() throws InterruptedException, KeeperException {
        List<String> lockPaths=null;
        lockPaths=client.getChildren(LOCK_ROOT_PATH,false);
        Collections.sort(lockPaths);
        int index = lockPaths.indexOf(lockPath.substring(LOCK_ROOT_PATH.length() + 1));
        if(index==0){
            System.out.println(Thread.currentThread().getName()+"锁获得，lockPath:"+lockPath);
            return;
        }
        String preLockPath=lockPaths.get(index-1);
        if (client.exists(LOCK_ROOT_PATH+"/"+preLockPath, watcher)==null){
            attemptLock();
        }
        System.out.println("等待当前锁释放,preLockPath："+preLockPath);
        synchronized (watcher){
            watcher.wait();
        }
        attemptLock();
    }

    public void releaseLock() throws InterruptedException, KeeperException {
        client.delete(lockPath, -1);
        client.close();
        System.out.println("锁释放："+lockPath);
    }
}