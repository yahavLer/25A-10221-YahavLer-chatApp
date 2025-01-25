package com.example.chatlibrary.controllers;

import com.example.chatlibrary.API.ChatAPI;
import com.example.chatlibrary.model.Chat;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatController {
    private static final String BASE_URL = "http://localhost:8080/api/";

    private ChatAPI getAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                                new GsonBuilder()
                                        .setLenient()
                                        .create()
                        )
                )
                .build();
        return retrofit.create(ChatAPI.class);
    }

    public void createChat(Chat chat, String user1Id, String user2Id) {
        ChatAPI chatAPI = getAPI();
        Call<Chat> call = chatAPI.createChat(chat, user1Id, user2Id);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    Chat chat = response.body();
                    System.out.println("Chat created: " + chat);
                } else {
                    System.out.println("Failed to create chat: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                System.out.println("Failed to create chat: " + t.getMessage());
            }
        });
    }

    public void getChatByChatId(String chatId) {
        ChatAPI chatAPI = getAPI();
        Call<Chat> call = chatAPI.getChatByChatId(chatId);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    Chat chat = response.body();
                    System.out.println("Chat found: " + chat);
                } else {
                    System.out.println("Failed to find chat: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                System.out.println("Failed to find chat: " + t.getMessage());
            }
        });
    }

    public void getChatsByUserId(String userId) {
        ChatAPI chatAPI = getAPI();
        Call<List<Chat>> call = chatAPI.getChatsByUserId(userId);
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                if (response.isSuccessful()) {
                    List<Chat> chats = response.body();
                    System.out.println("Chats found: " + chats);
                } else {
                    System.out.println("Failed to find chats: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                System.out.println("Failed to find chats: " + t.getMessage());
            }
        });
    }

    public void getChatBetweenUsers(String user1Id, String user2Id) {
        ChatAPI chatAPI = getAPI();
        Call<Chat> call = chatAPI.getChatBetweenUsers(user1Id, user2Id);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    Chat chat = response.body();
                    System.out.println("Chat found: " + chat);
                } else {
                    System.out.println("Failed to find chat: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                System.out.println("Failed to find chat: " + t.getMessage());
            }
        });
    }

    public void getAllChats(int size, int page) {
        ChatAPI chatAPI = getAPI();
        Call<List<Chat>> call = chatAPI.getAllChats(size, page);
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                if (response.isSuccessful()) {
                    List<Chat> chats = response.body();
                    System.out.println("Chats found: " + chats);
                } else {
                    System.out.println("Failed to find chats: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                System.out.println("Failed to find chats: " + t.getMessage());
            }
        });
    }

    public void deleteAll() {
        ChatAPI chatAPI = getAPI();
        Call<Chat> call = chatAPI.deleteAll();
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    Chat chat = response.body();
                    System.out.println("Chat deleted: " + chat);
                } else {
                    System.out.println("Failed to delete chat: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                System.out.println("Failed to delete chat: " + t.getMessage());
            }
        });
    }

}
