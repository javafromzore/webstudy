import org.apache.zookeeper.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ZookeeperTest {
    @Test
    public void zookeeper() throws IOException, InterruptedException, KeeperException {
        ZooKeeper client = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 300000000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("事件类型为:" + watchedEvent.getType());
                System.out.println("事件发生的路径:" + watchedEvent.getPath());
                System.out.println("事件的通知状态:" + watchedEvent.getState());
            }
        });
//        client.create("/admin/test", "null".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("节点的状态为" + client.exists("/admin/test", true));
        client.setData("/admin/test", "version-1".getBytes(), 1);
        client.setData("/admin/test", "version1".getBytes(), 0);
//        client.setData("/admin/study", "data".getBytes(), 1);
        while (true) {

        }
    }
}
