package com.devicehive.core.proxy.payload;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class TopicSubscribePayload implements Payload {

    @SerializedName("t")
    private List<String> topics;

    @SerializedName("consumer_group")
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
