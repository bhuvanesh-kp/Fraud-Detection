package com.bhuvanesh.kafka_streams.serializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.bhuvanesh.kafka_streams.events.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TansactionSerializer implements Serializer<Transaction>{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Transaction data) {   
        try {
            return mapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing transaction: ", e);
        }
    }
    
}
