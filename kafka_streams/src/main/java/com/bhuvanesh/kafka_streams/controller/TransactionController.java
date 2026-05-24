package com.bhuvanesh.kafka_streams.controller;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhuvanesh.kafka_streams.events.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public String sendTransaction() throws Exception{
        for (int i = 0; i < 50; i++) {

            String transactionId = "txn-" + System.currentTimeMillis() + "-" + i;
            Double amount = 8000 + new Random().nextDouble() * (11000 - 8000);

            Transaction txn = new Transaction(
                   transactionId,
                   "USER_" + i,
                   amount, 
                   LocalDateTime.now().toString(), 
                   transactionId, 
                   null
            );

           //String txnJson = mapper.writeValueAsString(txn);

           kafkaTemplate.send("transactions", transactionId, txn);
        }

        return "Transactions completed";
    }
}   