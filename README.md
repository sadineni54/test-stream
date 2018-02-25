# test-stream


## create 2 topic ("stream", "test2")

 bin/kafka-console-producer.sh --broker-list localhost:9092 --topic stream --property "parse.key=true" --property "key.separator=-"
     {"key1":"a1","key2":"a2"}-{"val1":"v1","val2":"v2"}
     {"key1":"a1","key2":"a2"}-{"val1":"v1","val2":"v2"}
     {"key1":"a1","key2":"a3"}-{"val1":"v1","val2":"v3"}

### Data in stream topic
     bin/kafka-console-consumer.sh --topic stream --zookeeper localhost:2181 --from-beginning --property print.key=true

    {"key1":"a1","key2":"a2"}       {"val1":"v1","val2":"v2"}
    {"key1":"a1","key2":"a2"}       {"val1":"v1","val2":"v2"}
    {"key1":"a1","key2":"a3"}       {"val1":"v1","val2":"v3"}

### Data in  test2 topic
     bin/kafka-console-consumer.sh --topic test2 --zookeeper localhost:2181 --from-beginning --property print.key=true
    {"key1":"a1","key2":"a2"}       2
    {"key1":"a1","key2":"a3"}       1
