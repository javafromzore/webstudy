package org.test.user.controller;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.zookeeper.KeeperException;
import org.redisson.api.RLock;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.test.mail.Mail;
import org.test.redis.consts.RedisKeyConsts;
import org.test.sms.Sms;
import org.test.user.vo.user.UserVO;
import org.test.user.service.UserService;
import org.test.common.model.vo.Result;
import org.test.zookeeper.lock.JusticeLock;
import org.test.zookeeper.lock.UnJusticeLock;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.Serializable;

import static org.test.mail.Mail.javaMailSender;

@Slf4j
@RestController
@RequestMapping("/user/user")
public class UserController implements Serializable {
    //    @Autowired在初始化ioc时作用，用于注入对象
//    @Autowired
//    @Qualifier("noTransKafkaTemplate")
//    private KafkaTemplate kafkaTemplate;


    @Autowired
    private KafkaTemplate kafkaTemplate;

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

    @GetMapping("/sendMsg")
    public Result sendMsg(@RequestParam("msg") String msg) {
        kafkaTemplate.send("topicA", "topicA-key", msg);
        return Result.succeed();
    }

    @GetMapping("/smsTest")
    public Result smsTest() throws ClientException {
        SendSmsResponse response=Sms.sendSms("13006725131", "1234", "SMS_154950909", "阿里云短信测试");
        System.out.println(response.getBizId());;
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
        System.out.println(response.getRequestId());
        return Result.succeed();
    }

    @GetMapping("/mailTest")
    public Result mailTest() {
        try {
            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom("2917462604@qq.com","我是发送人名字");
            helper.setTo("2917462604@qq.com");
            helper.setSubject("测试邮件");
            helper.setText("测试邮件内容",true);
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("邮件发送失败", e.getMessage());
            e.printStackTrace();
            return Result.failed("邮件发送失败");
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