package aibles.poc.kafkaproducerretries.controller;

import aibles.poc.kafkaproducerretries.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/send-message")
    public String sendMessage() {
        kafkaProducerService.sendUserInfo("data");
        return "success";
    }
}