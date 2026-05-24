package com.bhuvanesh.kafka_streams.events;

import java.util.List;

public record Transaction(
    String transactionId,
    String userId,
    double amount,
    String location,
    String type,
    List<Items> items
) {
    
}
