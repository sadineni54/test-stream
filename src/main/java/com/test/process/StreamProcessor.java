package com.test.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.domain.Key;
import com.test.domain.Value;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("inputStream")
public class StreamProcessor {

    @Autowired
    @Qualifier("inputStream")
    KStream<Key, Value> inputStream;

    @PostConstruct
    void process() {
        JsonSerde<Key> keySerde = new JsonSerde<Key>(Key.class, new ObjectMapper());
        JsonSerde<Value> valSerde = new JsonSerde<Value>(Value.class, new ObjectMapper());

        inputStream.groupByKey(keySerde, valSerde)

                .count(TimeWindows.of(500),
                        "windowStore")
                .toStream()
                .map((id, value) -> new KeyValue<>(id.key(), String.valueOf(value)))
                .to(keySerde, Serdes.String(), "test2");
    }

}
