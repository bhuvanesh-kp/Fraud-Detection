package com.bhuvanesh.kafka_streams.events;

public record Items(
    String itemId,
    String name,
    Double price,
    Integer quantity
) {
    
}
