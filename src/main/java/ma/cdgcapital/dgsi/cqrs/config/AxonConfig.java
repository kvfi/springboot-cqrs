package ma.cdgcapital.dgsi.cqrs.config;

import com.mongodb.MongoClient;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureAfter(AxonAutoConfiguration.class)
public class AxonConfig {

    @Bean
    public EventStorageEngine storageEngine(MongoClient client) {
        return MongoEventStorageEngine.builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).build();
    }

    @Bean
    public TokenStore myCustomTokenStore() {
        return MongoTokenStore.builder()
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(new MongoClient()).build())
                .serializer(JacksonSerializer.defaultSerializer())
                .build();
    }
}