package com.devicehive.core.proxy.payload;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class TopicsPayload implements Payload {

    @SerializedName("t")
    private List<String> topics;

    public TopicsPayload(List<String> topics) {
        this.topics = topics;
    }

    public TopicsPayload(String topic) {
        this.topics = Collections.singletonList(topic);
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "TopicsPayload{" +
                "topics=" + topics +
                '}';
    }
}
