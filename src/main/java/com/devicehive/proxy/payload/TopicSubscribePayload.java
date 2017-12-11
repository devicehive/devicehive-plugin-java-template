package com.devicehive.proxy.payload;

import com.devicehive.proxy.payload.Payload;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class TopicSubscribePayload implements Payload {

    @JsonProperty("t")
    private List<String> topics;

    @JsonProperty("consumer_group")
    private String consumerGroup = "request-consumer-group";

    public TopicSubscribePayload(List<String> topics) {
        this.topics = topics;
    }

    public TopicSubscribePayload(String topic) {
        this.topics = Collections.singletonList(topic);
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    @Override
    public String toString() {
        return "TopicSubscribePayload{" +
                "topics=" + topics +
                ", consumerGroup='" + consumerGroup + '\'' +
                '}';
    }
}
