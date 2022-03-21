package org.kie.kogito.jitexecutor.tester;

import org.kie.kogito.jitexecutor.tester.config.KafkaConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;

@ApplicationScoped
public class KafkaUtil {

    @Inject
    KafkaConfig config;

    public Properties createConsumerProperties() {
        Properties props = createCommonProperties();
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public Properties createProducerProperties() {
        Properties props = createCommonProperties();
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public Properties createCommonProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", config.bootstrap().servers());
        props.put("group.id", "group-id");
        return props;
    }
}
