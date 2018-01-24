package com.devicehive.core.proxy.payload;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class SubscribePayload implements Payload {

    @SerializedName("t")
    private List<String> topics;

//    @SerializedName("sg")
//    private String subscriptionGroup;

    public SubscribePayload(List<String> topics) {
        this.topics = topics;
    }

    public SubscribePayload(String topic) {
        this.topics = Collections.singletonList(topic);
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

//    public String getSubscriptionGroup() {
//        return subscriptionGroup;
//    }
//
//    public void setSubscriptionGroup(String subscriptionGroup) {
//        this.subscriptionGroup = subscriptionGroup;
//    }

    @Override
    public String toString() {
        return "SubscribePayload{" +
                "topics=" + topics +
                '}';
    }
}
