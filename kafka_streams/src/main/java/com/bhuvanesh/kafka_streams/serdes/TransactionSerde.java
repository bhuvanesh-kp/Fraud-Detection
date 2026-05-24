package com.bhuvanesh.kafka_streams.serdes;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import com.bhuvanesh.kafka_streams.events.Transaction;

public class TransactionSerde extends Serdes.WrapperSerde<Transaction>{

    public TransactionSerde(Serializer<Transaction> serializer, Deserializer<Transaction> deserializer) {
        super(serializer, deserializer);
    }
    
}
