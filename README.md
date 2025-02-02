# Chat Management System (Android App, API, & SDK)

The **Chat Management System** is a real-time communication solution, comprising an Android application, a RESTful API service, and an Android SDK. This project allows users to connect, create chats with new people, and send and receive messages instantly. The system includes robust API endpoints and an SDK that simplifies integration.

---

## **Project Architecture**

The project consists of the following key components:

### **1. API Service**
The API provides the backend infrastructure for managing users, chats, and messages.  
Main endpoints include:

- **User Management**  
  Create and retrieve user data.  
  Relevant API file: [`UserAPI.java`](docs/UserAPI.md)  
  Example endpoints:
    - `POST /api/users/create`: Creates a new user.
    - `GET /api/users/all`: Retrieves all users.

- **Chat Management**  
  Handle chat creation and retrieval.  
  Relevant API file: [`ChatAPI.java`](docs/ChatAPI.md)  
  Example endpoints:
    - `POST /api/chats/create`: Creates a new chat.
    - `GET /api/chats/user/{userId}`: Retrieves all chats for a user.

- **Message Management**  
  Manage sending and receiving of messages.  
  Relevant API file: [`MessageAPI.java`](docs/MessageAPI.md)  
  Example endpoints:
    - `POST /api/messages/send`: Sends a new message.
    - `GET /api/messages/user/{userId}`: Retrieves messages by user.

---

### **2. Android SDK**

The SDK simplifies interaction with the API by abstracting API calls into easy-to-use methods.  
Main features provided by the SDK include:

- **User Management:** Create users and fetch user data.
- **Chat Management:** Create chats, fetch chat details, and list a user's chats.
- **Message Management:** Send messages and retrieve messages for specific conversations.

---

## **3. Android Example Application**

The Android app demonstrates the full integration of the chat system. Key screens and features include:

- **Chat List Screen:** Displays all chats of the current user.
- **Chat Screen:** Allows users to send and receive messages in real-time.
- **User List Screen:** Allows users to create new chats by selecting a user from the list.

---

## **Features**

- **User Registration:** Create new user accounts.
- **Chat Management:** Create and list chats for a user.
- **Real-time Messaging:** Send and receive messages in real-time.
- **Expandable SDK:** The system is designed to allow easy extension and customization.

---

## **Setup Instructions**

### **Requirements:**
- **Android Studio** (version 4.0 or higher)
- **Java Development Kit (JDK)** (version 8 or higher)
- **Backend Server:** The API should be deployed and accessible.

### **Steps to Run:**

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yahavLer/25A-10221-YahavLer-chatApp.git

#### **Example usage in Java:**
```java
chatSDK.getChatsByUserId(currentUserId, new Callback_chat<List<Chat>>() {
    @Override
    public void onSuccess(List<Chat> chats) {
        // Successfully retrieved chats
        for (Chat chat : chats) {
            System.out.println("Chat ID: " + chat.getId());
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        // Handle failure
        System.err.println("Error fetching chats: " + errorMessage);
    }
});