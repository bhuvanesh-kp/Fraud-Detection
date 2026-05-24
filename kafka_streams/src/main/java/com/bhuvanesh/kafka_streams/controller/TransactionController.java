package com.bhuvanesh.kafka_streams.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhuvanesh.kafka_streams.events.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    // @PostMapping
    // public String sendTransaction() throws Exception{
    //     for (int i = 0; i < 50; i++) {

    //         String transactionId = "txn-" + System.currentTimeMillis() + "-" + i;
    //         Double amount = 8000 + new Random().nextDouble() * (11000 - 8000);

    //         Transaction txn = new Transaction(
    //                transactionId,
    //                "USER_" + i,
    //                amount, 
    //                LocalDateTime.now().toString(), 
    //                transactionId, 
    //                null
    //         );

    //        //String txnJson = mapper.writeValueAsString(txn);

    //        kafkaTemplate.send("transactions", transactionId, txn);
    //     }

    //     return "Transactions completed";
    // }

    @PostMapping("/publish")
    public String publishTransaction() {
        List<Transaction> txn = readTransactionFromResource();

        for (Transaction t: txn){
            kafkaTemplate.send("transaction", t.transactionId(), t);
        }

        return "Publishted " + txn.size() + " transaction to kafka";
    }

    private List<Transaction> readTransactionFromResource() {
        try (InputStream is = getClass().getResourceAsStream("/static/transactions.json")) {
            return mapper.readValue(is, new TypeReference<List<Transaction>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse transactions.json", e);
        }
    }
    
}   