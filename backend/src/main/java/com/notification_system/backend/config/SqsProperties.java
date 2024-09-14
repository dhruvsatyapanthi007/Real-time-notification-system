package com.notification_system.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sqs")
public class SqsProperties {
    private String sendQueueUrl;
    private String receiveQueueUrl;

    // Getters and setters
    public String getSendQueueUrl() {
        return sendQueueUrl;
    }

    public void setSendQueueUrl(String sendQueueUrl) {
        this.sendQueueUrl = sendQueueUrl;
    }

    public String getReceiveQueueUrl() {
        return receiveQueueUrl;
    }

    public void setReceiveQueueUrl(String receiveQueueUrl) {
        this.receiveQueueUrl = receiveQueueUrl;
    }
}
