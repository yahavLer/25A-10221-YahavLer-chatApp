package com.example.chatlibrary;

import com.example.chatlibrary.controllers.ChatController;
import com.example.chatlibrary.controllers.MessageController;
import com.example.chatlibrary.controllers.UserController;
import com.example.chatlibrary.model.Chat;
import com.example.chatlibrary.model.Message;
import com.example.chatlibrary.model.User;

public class chatSDK {
    private static final ChatController chatController = new ChatController();
    private static final UserController userController = new UserController();
    private static final MessageController messageController = new MessageController();

    public static void createChat(Chat chat, String user1Id, String user2Id) {
        chatController.createChat(chat, user1Id, user2Id);
    }

    public static void getChatByChatId(String chatId) {
        chatController.getChatByChatId(chatId);
    }

    public static void getChatsByUserId(String userId) {
        chatController.getChatsByUserId(userId);
    }

    public static void getChatBetweenUsers(String user1Id, String user2Id) {
        chatController.getChatBetweenUsers(user1Id, user2Id);
    }

    public static void getAllChats(int size, int page) {
        chatController.getAllChats(size, page);
    }

    public static void deleteAll() {
        chatController.deleteAll();
    }

    public static void createUser(User username) {
        userController.createUser(username);
    }

    public static void getUserByUserId(String userId) {
        userController.getById(userId);
    }

    public static void getAllUsers(int size, int page) {
        userController.getAllUsers(size, page);
    }

    public static void sendMessage(Message message) {
        messageController.sendMessage(message);
    }

    public static void getMessageById(String messageId) {
        messageController.getMessageById(messageId);
    }

    public static void getMessagesByConversationId(String chatId) {
        messageController.getMessagesByConversationId(chatId);
    }

    public static void getAllMessages(int size, int page) {
        messageController.getAllMessages(size, page);
    }

    public static void deleteAllMessages() {
        messageController.deleteAll();
    }

}
