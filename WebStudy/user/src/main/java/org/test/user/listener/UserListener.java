package org.test.user.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener {
    @KafkaListener(id="getMsg", topics = "topicA", groupId = "undefaultGroup")
    public String getMsg(String s) {
        System.out.println(s);
        return "msg:"+s;
    }

    @KafkaListener(id="getMsg2", topics = "topicB", groupId = "defaultGroup")
    public String getMsg2(String s) {
        System.out.println(s);
        return "msg:"+s;
    }
}