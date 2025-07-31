package com.bike.public_bike.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplte){
        this.kafkaTemplate = kafkaTemplte;
    }

    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
    }

}
