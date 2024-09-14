package com.notification_system.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;

@Component
public class QueueProcessor {
    @Value("${sqs.receiveQueueUrl}")
    private String receiveQueueUrl;

    @Value("${dynamodb.tableName}")
    private String tableName;

    private final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    private final DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());

    public void processMessages() {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
            .withQueueUrl(receiveQueueUrl)
            .withMaxNumberOfMessages(10)
            .withWaitTimeSeconds(20);

        for (com.amazonaws.services.sqs.model.Message message : sqs.receiveMessage(receiveMessageRequest).getMessages()) {
            String messageBody = message.getBody();
            storeNotification(messageBody);
            sqs.deleteMessage(new DeleteMessageRequest(receiveQueueUrl, message.getReceiptHandle()));
        }
    }

    private void storeNotification(String messageBody) {
        Table table = dynamoDB.getTable(tableName);
        String notificationId = generateUniqueId(); // Implement this method
        table.putItem(new Item()
            .withPrimaryKey("NotificationID", notificationId)
            .withString("UserID", extractUserId(messageBody)) // Implement extractUserId method
            .withString("Content", "User action: " + messageBody) // Customize content as needed
            .withString("Timestamp", extractTimestamp(messageBody)) // Implement extractTimestamp method
            .withString("Status", "UNREAD")
        );
    }
}
