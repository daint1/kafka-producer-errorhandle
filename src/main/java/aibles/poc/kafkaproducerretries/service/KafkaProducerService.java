package aibles.poc.kafkaproducerretries.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaProducerService {


    @Autowired
    private KafkaTemplate kafkaTemplate;
    private long numberSendFailed = 0;
    public void sendUserInfo(String data) {

        for(int i = 0; i<1000; i++) {
            CompletableFuture<RecordMetadata> future = kafkaTemplate.send("test", data);

            future.whenComplete((recordMetadata, throwable) -> {
                if (recordMetadata != null) {
                    // The send operation was successful.
                    System.out.println("Record sent to partition " + recordMetadata.partition());
                } else {
                    // The send operation failed.
                    numberSendFailed++;
                    System.out.println("Failed to send record: " + throwable.getMessage());
                    System.out.println("numberSendFailed: " + String.valueOf(numberSendFailed));
                }
            });
        }


    }

}