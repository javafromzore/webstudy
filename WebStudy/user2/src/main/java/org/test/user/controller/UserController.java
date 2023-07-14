package org.test.user.controller;

import org.apache.zookeeper.KeeperException;
import org.redisson.api.RLock;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.test.redis.consts.RedisKeyConsts;
import org.test.user.vo.user.UserVO;
import org.test.user.service.UserService;
import org.test.common.model.vo.Result;
import org.test.zookeeper.lock.JusticeLock;
import org.test.zookeeper.lock.UnJusticeLock;

import java.io.IOException;
import java.io.Serializable;

@RestController
@RequestMapping("/user2/user")
public class UserController implements Serializable {
    //    @Autowired在初始化ioc时作用，用于注入对象
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    @Autowired
    private RedissonClient client;

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public Result<UserVO> getUser(@RequestParam("id") long id) {
        return Result.succeed(userService.getUser(id));
    }

    @PostMapping("/init")
    public Result init() {
        template.opsForValue().set(RedisKeyConsts.GOODS_COUNT.getKey(), String.valueOf(10000));
        return Result.succeed();
    }

    @GetMapping("/redisTest")
    public Result<String> redisTest() {
        RLock rLock = client.getLock(RedisKeyConsts.GOODS_COUNT_LOCK.getKey());
        try {
            rLock.lock();
            int number = Integer.parseInt((String) template.opsForValue().get(RedisKeyConsts.GOODS_COUNT.getKey()));
            number--;
            template.opsForValue().set(RedisKeyConsts.GOODS_COUNT.getKey(), number);
        } catch (RuntimeException e) {
            throw new RuntimeException("没有货了");
        } finally {
            rLock.unlock();
        }
        return Result.succeed();
    }

    @GetMapping("/redisMQTest")
    public Result<String> redisMQTest() {
        RQueue<String> queue = client.getQueue("Queue");
        queue.add("字符串1");
        queue.add("字符串2");
        queue.add("字符串3");
        queue.add("字符串4");
        queue.add("字符串5");
        return Result.succeed();
    }

    @GetMapping("/redisMQConsumerTest")
    public Result<String> redisMQConsumerTest() {
        RQueue<String> queue = client.getQueue("Queue");
        String s = queue.poll();
        String s1 = queue.poll();
        String s2 = queue.poll();
        String s3 = queue.poll();
        String s4 = queue.poll();
        String s5 = queue.poll();
        return Result.succeed(s5);
    }

    @GetMapping("/ticketSeller")
    public Result ticketSeller() throws IOException, InterruptedException, KeeperException {
        TicketSeller ticketSeller = new TicketSeller();
        for (int i = 0; i < 2; i++) {
            ticketSeller.sellTicketWithLock();
        }
        return Result.succeed();
    }

    private class TicketSeller {
        private void sell() {
            // 线程随机休眠数毫秒，模拟现实中的费时操作
            int sleepMillis = (int) (Math.random() * 2000);
            try {
                //调度器切换线程
                //代表复杂逻辑执行了一段时间
                Thread.sleep(sleepMillis);
                System.out.println("售出一张票");
                int newTickNums = (Integer) template.opsForValue().get("tickNums") - 1;
                template.opsForValue().set("tickNums", newTickNums);
                System.out.println("剩余" + newTickNums + "张票");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void sellTicketWithLock() throws KeeperException, InterruptedException, IOException {
            UnJusticeLock lock=new UnJusticeLock();
            lock.lock();
            sell();
            lock.unLock();
        }
    }
}