# Real-Time Notification System

## Overview

The Real-Time Notification System is a scalable application designed to provide real-time notifications to users. It utilizes AWS SQS for message queuing and AWS DynamoDB for storing notifications. The backend is implemented using Java with Spring Boot, while the frontend is built using React.

## Features

- **Real-Time Notifications**: Users receive notifications instantly using WebSockets.
- **Scalable Architecture**: Utilizes AWS SQS for message handling and DynamoDB for persistent storage.
- **Responsive UI**: Built with React, providing an interactive user experience.

## Backend

### Technologies

- Java
- Spring Boot
- AWS SQS
- AWS DynamoDB

### Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/notification-system.git
   cd notification-system/backend
   ```

2. **Configure AWS Credentials**

   Set up your AWS credentials in `src/main/resources/application.properties`:

   ```properties
   # AWS Configuration
   aws.region=us-east-1
   aws.accessKeyId=YOUR_AWS_ACCESS_KEY_ID
   aws.secretKey=YOUR_AWS_SECRET_KEY

   # SQS Configuration
   sqs.sendQueueUrl=https://sqs.us-east-1.amazonaws.com/YOUR_ACCOUNT_ID/NotificationRequestsQueue
   sqs.receiveQueueUrl=https://sqs.us-east-1.amazonaws.com/YOUR_ACCOUNT_ID/NotificationProcessingQueue

   # DynamoDB Configuration
   dynamodb.tableName=Notifications

   # WebSocket Configuration
   websocket.endpoint=/ws/notifications

   # Other Application Properties
   server.port=8080
   logging.level.root=INFO
   ```

3. **Build and Run**

   ```bash
   ./mvnw spring-boot:run
   ```

## Frontend

### Technologies

- React
- WebSocket (SockJS and Stomp.js)

### Setup

1. **Navigate to the Frontend Directory**

   ```bash
   cd notification-system/frontend
   ```

2. **Install Dependencies**

   ```bash
   npm install
   ```

3. **Configure WebSocket Endpoint**

   Update the WebSocket URL in `src/services/WebSocketService.js` if necessary:

   ```javascript
   const SOCKET_URL = 'http://localhost:8080/ws/notifications';
   ```

4. **Start the React Application**

   ```bash
   npm start
   ```

## Folder Structure

### Backend

- `src/main/java/com/notification_system/backend/config/` - WebSocket and AWS configuration
- `src/main/java/com/notification_system/backend/service/` - Business logic and message handling
- `src/main/resources/application.properties` - Configuration file for AWS and application settings

### Frontend

- `public/` - Static files (HTML, CSS)
- `src/assets/styles/` - Application styles
- `src/components/` - React components
- `src/services/` - WebSocket service
- `src/App.js` - Main React component
- `src/index.js` - Application entry point

## Contributing

Feel free to submit pull requests or issues. For contributions, please follow the standard GitHub workflow:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Create a new Pull Request
