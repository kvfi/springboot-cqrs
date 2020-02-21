package ma.cdgcapital.dgsi.cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class EventSourcingAxonSpringBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(EventSourcingAxonSpringBootApplication.class, args);
    }


}
