package com.notification_system.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;

@RestController
public class NotificationController {
    private final DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
    private static final String TABLE_NAME = "Notifications";

    @GetMapping("/notifications")
    public List<Notification> getNotifications(@RequestParam String userId) {
        Table table = dynamoDB.getTable(TABLE_NAME);
        // Example of how you might query for notifications
        Item item = table.getItem("UserID", userId);
        // Convert Item to Notification objects and return
        return convertItemToNotifications(item); // Implement this method
    }
}
