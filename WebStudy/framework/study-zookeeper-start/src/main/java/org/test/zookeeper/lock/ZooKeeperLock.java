package org.test.zookeeper.lock;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ZooKeeperLock {
    private ZooKeeper client;
    private static final String LOCK_ROOT_PATH="/Locks";
    private static final String LOCK_NODE_NAME="Lock_";
    //当前线程的锁路径，如果解锁或者会话失效，则删除节点
    private String lockPath;

    private Watcher watcher=new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
//            System.out.println(watchedEvent.getPath()+"锁释放");
            //todo 这里为什么要加锁？
//            synchronized (this) {
                notifyAll();
//            }
        }
    };

    public ZooKeeperLock() throws IOException {
        client=new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 10000000,
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
        //首先获得根节点下的所有子节点，并且排序
        List<String> lockPaths=client.getChildren(LOCK_ROOT_PATH,false);
        Collections.sort(lockPaths);
        //找到当前线程所对应节点的顺序、位置
        int index = lockPaths.indexOf(lockPath.substring(LOCK_ROOT_PATH.length() + 1));
        //如果当前线程所属节点是第一个节点，则直接运行该线程
        if(index==0){
//            System.out.println(Thread.currentThread().getName()+"锁获得，lockPath:"+lockPath);
            return;
        }
        //否则监听前一个节点，如果前一个节点不存在（运行到这时前一个节点刚好被删除），则重新判断（此时可以让其直接返回执行）
//        String preLockPath=lockPaths.get(index-1);
//        if (client.exists(LOCK_ROOT_PATH+"/"+preLockPath, watcher)==null){
//            attemptLock();
//        }
//        System.out.println("等待当前锁释放,preLockPath："+preLockPath);
//        synchronized (watcher){
//            watcher.wait();
        //todo 没有办法保证原子性，只能用延时等待来解决
        Thread.currentThread().wait(1000);
//        }
        attemptLock();
    }

    public void releaseLock() throws InterruptedException, KeeperException {
//        client.delete(lockPath, -1);
        client.close();
//        System.out.println("锁释放："+lockPath);
    }
}