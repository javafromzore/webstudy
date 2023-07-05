import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class Redis {

    static Jedis jedis;

    @BeforeAll
    static void setUp() {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    @Test
    void testString() {
        String result = jedis.set("action", " 一脚踢出了整一个盛夏");
        System.out.println("result:" + result);
        String name = jedis.get("action");
        System.out.println("name:" + name);
    }

    @Test
    void testHash() {
        long result = jedis.hset("map", "means", "love");
        System.out.println("result:" + result);
        String value = jedis.hget("map", "means");
        System.out.println("value:" + value);
    }

    @Test
    void testList() {
        String result = jedis.lset("list", 0, "love");
        System.out.println("result:" + result);
        String value = jedis.lindex("list", 0);
        System.out.println("value:" + value);
    }

    @Test
    void testSet() {
        long result = jedis.sadd("set", "love");
        System.out.println("result:" + result);
        Set<String> strings = jedis.smembers("set");
        for (String s : strings) {
            System.out.println(s);
        }
    }
}