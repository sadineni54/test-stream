package com.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.domain.Key;
import com.test.domain.Value;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
@EnableKafkaStreams
public class AppConfig {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public StreamsConfig kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        JsonSerde<Key> keySerde= new JsonSerde<Key>(Key.class,new ObjectMapper());
        JsonSerde<Value> valSerde = new JsonSerde<Value>(Value.class,new ObjectMapper());


        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "testStreams"+new Date().getTime());
       //props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, keySerde.getClass().getName());
       // props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,valSerde.getClass().getName());
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,keySerde.deserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,valSerde.deserializer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,keySerde.serializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valSerde.serializer());

        return new StreamsConfig(props);
    }

    @Bean(name="inputStream")
    public KStream<Key,Value> kStream(KStreamBuilder kStreamBuilder) {

        JsonSerde<Key> keySerde= new JsonSerde<Key>( Key.class,new ObjectMapper());
        JsonSerde<Value> valSerde = new JsonSerde<Value>(Value.class,new ObjectMapper());
        KStream<Key, Value> stream = kStreamBuilder.stream(keySerde,valSerde,"stream");

        return stream;
    }
}
