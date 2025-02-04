[![](https://jitpack.io/v/yahavLer/25A-10221-YahavLer-chatApp.svg)](https://jitpack.io/#yahavLer/25A-10221-YahavLer-chatApp)

# Chat SDK for Android

The Chat SDK is a flexible and easy-to-use library designed to simplify the creation and management of chat functionalities in Android applications. It provides core features like chat creation, user management, and message handling, with all operations performed asynchronously through callback-based methods.

## Table of Contents
1. [What's New](#whats-new)
2. [Key Features](#key-features)
3. [Architecture Overview](#architecture-overview)
4. [Installation](#installation)
5. [Permissions](#permissions)
6. [Initialization](#initialization)
7. [Usage Examples](#usage-examples)
8. [Error Handling](#error-handling)
9. [Use Cases](#use-cases)
10. [Known Limitations](#known-limitations)
11. [Contributing](#contributing)
12. [License](#license)

---

## What's New
Version 1.0.0:
- Initial release with core chat, user, and message operations.

---

## Key Features
- **Chat Management:** Create and retrieve chats, including filtering by user or chat ID.
- **User Management:** Register and manage users with simple API calls.
- **Messaging:** Send, retrieve, and delete messages asynchronously.
- **Callback-Based Design:** Handle success and failure responses efficiently.

---

## Architecture Overview
The SDK's architecture is structured around three core controllers:

- **ChatController:** Manages chat creation and retrieval operations.
- **UserController:** Handles user registration and information retrieval.
- **MessageController:** Manages sending and retrieving messages.

These controllers are accessed via the main `chatSDK` facade, which exposes various methods such as:

- `createChat(Chat chat, String user1Id, String user2Id, Callback_chat<Chat> callback)`
- `getUserByUserId(String userId, Callback_chat<User> callback)`
- `sendMessage(Message message, Callback_chat<Message> callback)`
- `createChat(Chat chat, String user1Id, String user2Id, Callback_chat<Chat> callback)`
- `getChatByChatId(String chatId, Callback_chat<Chat> callback)`
- `getChatsByUserId(String userId, Callback_chat<List<Chat>> callback)`
- `getChatBetweenUsers(String user1Id, String user2Id, Callback_chat<Chat> callback)`
- `getAllChats(int size, int page, Callback_chat<List<Chat>> callback)`
- `deleteAllChats(Callback_chat<Chat> callback)`
- `createUser(User user, Callback_chat<User> callback)`
- `getUserByUserId(String userId, Callback_chat<User> callback)`
- `getByUsernameAndPhone(String username,String phoneNumber, Callback_chat<User> callback)`
- `getAllUsers(int size, int page, Callback_chat<List<User>> callback)`
- `sendMessage(Message message, Callback_chat<Message> callback)`
- `getMessageById(String messageId, Callback_chat<Message> callback)`
- `getAllMessages(int size, int page, Callback_chat<List<Message>> callback)`
- `deleteAllMessages(Callback_chat<Message> callback)`

---

## Installation
### Add JitPack to Repositories
Add the following to your `settings.gradle` file:
```gradle
dependencyResolutionManagement {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

### Add the Dependency
Include the SDK in your `build.gradle` file:
```gradle
	dependencies {
	        implementation 'com.github.yahavLer:25A-10221-YahavLer-chatApp:1.0.0'
	}
```

---

## Permissions
Ensure your AndroidManifest.xml includes the following permissions:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

---

## Initialization
The `chatSDK` does not require explicit initialization. You can start using its methods directly.

---

## Usage Examples
Here are common operations you might perform with the Chat SDK. Each function uses a `Callback_chat<T>`, which has two methods:

- `onSuccess(T result)` – Called when the operation succeeds.
- `onFailure(String errorMessage)` – Called when an error occurs.

### 1. Create a Chat
```java
Chat chat = new Chat();
chat.setTitle("New Chat");
chatSDK.createChat(chat, "user1Id", "user2Id", new Callback_chat<Chat>() {
    @Override
    public void onSuccess(Chat result) {
        System.out.println("Chat created with ID: " + result.getChatId());
    }

    @Override
    public void onFailure(String errorMessage) {
        System.err.println("Failed to create chat: " + errorMessage);
    }
});
```

### 2. Retrieve a User by ID
```java
chatSDK.getUserByUserId("user1Id", new Callback_chat<User>() {
    @Override
    public void onSuccess(User result) {
        System.out.println("User retrieved: " + result.getUsername());
    }

    @Override
    public void onFailure(String errorMessage) {
        System.err.println("Failed to retrieve user: " + errorMessage);
    }
});
```

### 3. Send a Message
```java
Message message = new Message();
message.setContent("Hello World!");
message.setSenderId("user1Id");
message.setReceiverId("user2Id");

chatSDK.sendMessage(message, new Callback_chat<Message>() {
    @Override
    public void onSuccess(Message result) {
        System.out.println("Message sent successfully!");
    }

    @Override
    public void onFailure(String errorMessage) {
        System.err.println("Failed to send message: " + errorMessage);
    }
});
```

---

## Error Handling
All methods return errors through the `onFailure(String errorMessage)` callback. Common errors include:
- Network issues
- Invalid input data (e.g., user or chat ID not found)
- Server errors (5xx)

Tip: Use logs or user-friendly toasts/snackbars to handle errors gracefully.

---

## Use Cases
- **Chat Applications:** Implement messaging features in social apps.
- **Customer Support Systems:** Allow users to chat with support agents.
- **Team Collaboration Tools:** Enable real-time messaging between team members.

---

## Known Limitations
- The SDK does not currently support media (e.g., image or file sharing).
- No built-in push notifications.
- Pagination is supported but limited to simple queries.

---

## Contributing
We welcome contributions! To contribute:
1. Fork the repo.
2. Create a new branch.
3. Make your changes and commit them.
4. Submit a pull request with a detailed description.

---

## License
Distributed under the MIT License. See the LICENSE file for more information.

---

## Questions or Feedback?
Feel free to open an issue on GitHub or contact the project maintainer if you have any questions or suggestions.

Happy coding with Chat SDK! 🚀

