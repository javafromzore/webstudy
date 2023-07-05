import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.network.KafkaChannel;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class KafKa {
    private KafkaProducer<String, String> kafkaProducer;

    @BeforeEach
    public void createClient(){
        Properties properties=new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:2");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        kafkaProducer=new KafkaProducer<>(properties);
    }

    @Test
    public void kafka() throws ExecutionException, InterruptedException {
        String uuid= UUID.randomUUID().toString();
        RecordMetadata recordMetadata=null;
        recordMetadata=kafkaProducer.send(new ProducerRecord<>("topic-B", uuid)).get();
        System.out.println("recordMetadata:"+recordMetadata);
        System.out.println("uuid:"+uuid);
    }
}
