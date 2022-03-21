package org.kie.kogito.jitexecutor.tester.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kie.kogito.jitexecutor.tester.KafkaUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class KafkaService {

    @Inject
    KafkaUtil kafkaUtil;

    public String pushAndPoll(String message, String outputTopic, String inputTopic, Integer timeout) throws Exception {
        push(message, outputTopic);
        return poll(inputTopic, timeout);
    }

    public String pushAndPoll(String message, String outputTopic, String inputTopic) throws Exception {
        return pushAndPoll(message,outputTopic,inputTopic,5000);
    }

    public void push(String message, String outputTopic) throws ExecutionException, InterruptedException {
        try (KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(kafkaUtil.createProducerProperties())) {
            kafkaProducer.send(new ProducerRecord<String, String>(outputTopic, outputTopic, message)).get();
        }
    }

    public String poll(String inputTopic, Integer timeout){
        String value = null;
        try (KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(kafkaUtil.createConsumerProperties())) {
            kafkaConsumer.subscribe(Collections.singleton(inputTopic));
            for (ConsumerRecord<String, String> record : kafkaConsumer.poll(Duration.ofMillis(timeout))) {
                value = record.value();
            }
        }
        return value;
    }
}
