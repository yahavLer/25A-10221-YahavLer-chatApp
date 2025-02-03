
# Chat SDK Documentation

Welcome to the documentation for the Chat SDK project. This guide provides detailed instructions for setting up and using the Chat SDK, API service, and the example Android application. It includes setup instructions, usage examples, and diagrams to help developers integrate and work with the SDK effectively.

## Table of Contents
1. [Overview](#overview)
2. [Project Setup](#project-setup)
3. [API Service Documentation](#api-service-documentation)
4. [Chat SDK Documentation](#chat-sdk-documentation)
5. [Example Application Setup](#example-application-setup)
6. [FAQ](#faq)
7. [Contributing](#contributing)
8. [License](#license)

---

## Overview
The Chat SDK project consists of:
- **API Service:** A backend service to handle chat operations, deployed on Koyeb.
- **Chat SDK:** An Android library for integrating chat functionality.
- **Example Application:** An Android app demonstrating the use of the Chat SDK.

This documentation explains how to set up, configure, and use these components.

---

## Project Setup
### Prerequisites
Ensure you have the following installed:
- **Java Development Kit (JDK)** 8 or higher
- **Android Studio**
- **MongoDB Atlas** account (for database access)
- **Internet access** for API service requests

### Cloning the Repository
Clone the project from GitHub:
```bash
git clone https://github.com/yahavLer/25A-10221-YahavLer-chatApp.git
```

### Directory Structure
```
chatApp/
|-- api-service/
|-- chat-sdk/
|-- example-application/
|-- docs/
```

---

## API Service Documentation
The API service is responsible for handling chat, user, and message operations. It is deployed on **Koyeb** and connects to a **MongoDB Atlas** database.

### Endpoints Overview
Here are the main API endpoints:

| Endpoint              | Method | Description                          |
|-----------------------|--------|--------------------------------------|
| `/api/chats`          | POST   | Create a new chat                    |
| `/api/chats/{chatId}` | GET    | Retrieve a chat by ID                |
| `/api/users`          | POST   | Register a new user                  |
| `/api/users/{userId}` | GET    | Retrieve user information by ID      |
| `/api/messages`       | POST   | Send a new message                   |
| `/api/messages/{msgId}` | GET  | Retrieve a message by ID             |

### Sample Request
**POST /api/chats**
```json
{
    "title": "New Chat",
    "participants": ["user1", "user2"]
}
```
**Response:**
```json
{
    "chatId": "abc123",
    "title": "New Chat",
    "createdAt": "2025-02-03T10:00:00Z"
}
```

---

## Chat SDK Documentation
The Chat SDK provides the following core functionalities:

- **Chat Management:** Creating, retrieving, and deleting chats.
- **User Management:** Registering and retrieving user details.
- **Messaging:** Sending and retrieving messages.

### Installation
Add the following to your `settings.gradle`:
```gradle
dependencyResolutionManagement {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency in `build.gradle`:
```gradle
dependencies {
    implementation("com.github.yahavLer:chatSDK:1.0.0")
}
```

### Example Usage
#### Create a Chat
```java
Chat chat = new Chat();
chat.setTitle("Project Discussion");
chatSDK.createChat(chat, "user1Id", "user2Id", new Callback_chat<Chat>() {
    @Override
    public void onSuccess(Chat result) {
        System.out.println("Chat created: " + result.getChatId());
    }

    @Override
    public void onFailure(String errorMessage) {
        System.err.println("Failed to create chat: " + errorMessage);
    }
});
```

#### Send a Message
```java
Message message = new Message();
message.setContent("Hello!");
message.setSenderId("user1Id");
message.setReceiverId("user2Id");

chatSDK.sendMessage(message, new Callback_chat<Message>() {
    @Override
    public void onSuccess(Message result) {
        System.out.println("Message sent: " + result.getMessageId());
    }

    @Override
    public void onFailure(String errorMessage) {
        System.err.println("Failed to send message: " + errorMessage);
    }
});
```

---

## Example Application Setup
The example application demonstrates the integration of the Chat SDK. Follow these steps to run the application:

1. Open the project in **Android Studio**.
2. Configure your **API base URL** in the app's configuration file.
3. Run the application on an emulator or physical device.

---

## FAQ
**Q: What should I do if I encounter API errors?**
- Ensure your internet connection is stable.
- Verify that the API service is running.
- Check the logs for detailed error messages.

**Q: Can I extend the SDK with custom features?**
- Yes, you can fork the repository and modify the SDK's source code.

---

## Contributing
We welcome contributions! To contribute:
1. Fork the repository.
2. Create a new branch.
3. Implement your changes and commit them.
4. Submit a pull request.

---

## License
Distributed under the MIT License. See the LICENSE file for details.

---

## Access Documentation Online
This documentation is published on GitHub Pages. You can view it online at: [Project Documentation](https://yahavLer.github.io/25A-10221-YahavLer-chatApp)

Happy coding! 🚀
