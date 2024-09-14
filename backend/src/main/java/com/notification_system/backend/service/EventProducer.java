package com.notification_system.backend.service;

import com.notification_system.backend.config.SqsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Component
public class EventProducer {
    private final SqsProperties sqsProperties;
    private final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

    @Autowired
    public EventProducer(SqsProperties sqsProperties) {
        this.sqsProperties = sqsProperties;
    }

    public void sendMessage(String messageBody) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
            .withQueueUrl(sqsProperties.getSendQueueUrl())
            .withMessageBody(messageBody);
        sqs.sendMessage(sendMessageRequest);
    }
}
