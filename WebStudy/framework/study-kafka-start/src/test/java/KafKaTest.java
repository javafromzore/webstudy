import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.concurrent.Future;


public class KafKaTest {
    @Test
    void kafkaTest() {
        Properties properties = new Properties();
        //kafka服务地址,集群环境可以设置多个，用逗号隔开
        properties.put("bootstrap.servers","127.0.0.1:9092");
        //kafka接收消息只认识字节数组，定义消息的key的序列化器和value的序列化器，将其转化成字节数组
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //创建kafka生产者对象，并将属性传给生产者
        try (
                KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);){
            //生产者的消息存放在ProducerRecord对象中
            ProducerRecord<String,String> record;
            try {
                //TODO 发送4条消息
                for(int i=0;i<4;i++){
                    //设定消息发送的主题，消息的key值和消息的内容
                    record = new ProducerRecord<String,String>("asy", null,"lison");
                    //获取send的返回值可以得知消息发送情况
                    //一直发送直到发送成功
                    RecordMetadata metadata=null;
                    while (metadata==null) {
                        //发送消息也可以异步发送
                        Future<RecordMetadata> future = producer.send(record, new Callback() {
                            @Override
                            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                                if (e!=null) {
                                    System.out.println("发生异常");
                                    e.printStackTrace();
                                }
                                if (recordMetadata==null){
                                    System.out.println("发送失败！");
                                }
                                System.out.println("offset："+recordMetadata.offset());
                                System.out.println("partition："+recordMetadata.partition());
                            }
                        });
                        metadata=future.get();
                    }
                    System.out.println(i+"，message is sent");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                producer.close();
            }
        }
    }
}