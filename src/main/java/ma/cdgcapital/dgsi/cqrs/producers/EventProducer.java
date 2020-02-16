package ma.cdgcapital.dgsi.cqrs.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.Properties;

public class EventProducer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);

    @Value("${axon.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

    private String message;
    private String topic;

    public EventProducer(String topic, String message) {
        this.message = message;
        this.topic = topic;
    }

    @Override
    public void run() {

        Properties props = new Properties();
        props.put("bootstrap.servers", this.kafkaBootstrapServers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        producer.send(new ProducerRecord<>(this.topic, this.message));

        logger.info("Message sent to topic: {}", this.topic);

        producer.close();
    }

}