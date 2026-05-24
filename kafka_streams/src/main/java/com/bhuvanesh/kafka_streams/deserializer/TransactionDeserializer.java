package com.bhuvanesh.kafka_streams.deserializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.bhuvanesh.kafka_streams.events.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionDeserializer implements Deserializer<Transaction>{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Transaction deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, Transaction.class);
        } catch (Exception e) {
            throw new SerializationException("Error Deserialarization message: ", e);
        }
    }
    
}
