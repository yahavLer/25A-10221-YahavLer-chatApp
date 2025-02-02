package com.example.chatlibrary;

import com.example.chatlibrary.controllers.ChatController;
import com.example.chatlibrary.controllers.MessageController;
import com.example.chatlibrary.controllers.UserController;
import com.example.chatlibrary.model.Chat;
import com.example.chatlibrary.model.Message;
import com.example.chatlibrary.model.User;

import java.util.List;

public class chatSDK {
    private static final ChatController chatController = new ChatController();
    private static final UserController userController = new UserController();
    private static final MessageController messageController = new MessageController();

    public static void createChat(Chat chat, String user1Id, String user2Id, Callback_chat<Chat> callback) {
        chatController.createChat(chat, user1Id, user2Id, callback);
    }

    public static void getChatByChatId(String chatId, Callback_chat<Chat> callback) {
        chatController.getChatByChatId(chatId, callback);
    }

    public static void getChatsByUserId(String userId, Callback_chat<List<Chat>> callback) {
        chatController.getChatsByUserId(userId, callback);
    }

    public static void getChatBetweenUsers(String user1Id, String user2Id, Callback_chat<Chat> callback) {
        chatController.getChatBetweenUsers(user1Id, user2Id, callback);
    }

    public static void getAllChats(int size, int page, Callback_chat<List<Chat>> callback) {
        chatController.getAllChats(size, page, callback);
    }

    public static void deleteAllChats(Callback_chat<Chat> callback) {
        chatController.deleteAll(callback);
    }

    public static void createUser(User user, Callback_chat<User> callback) {
        userController.createUser(user, callback);
    }

    public static void getUserByUserId(String userId, Callback_chat<User> callback) {
        userController.getById(userId, callback);
    }

    public static void getByUsernameAndPhone(String username,String phoneNumber, Callback_chat<User> callback){
        userController.getByUsernameAndPhone(username, phoneNumber, callback);
    }

    public static void getAllUsers(int size, int page, Callback_chat<List<User>> callback) {
        userController.getAllUsers(size, page, callback);
    }

    public static void sendMessage(Message message, Callback_chat<Message> callback) {
        messageController.sendMessage(message, callback);
    }

    public static void getMessageById(String messageId, Callback_chat<Message> callback) {
        messageController.getMessageById(messageId, callback);
    }

    public static void getAllMessages(int size, int page, Callback_chat<List<Message>> callback) {
        messageController.getAllMessages(size, page, callback);
    }

    public static void deleteAllMessages(Callback_chat<Message> callback) {
        messageController.deleteAll(callback);
    }

}
