package com.example.chatlibrary.controllers;

import com.example.chatlibrary.API.ChatAPI;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.model.Chat;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatController {
//    private static final String BASE_URL = "http://localhost:8080/api/";
    private static final String BASE_URL = "https://favourable-lorrie-yahavler-7d646b21.koyeb.app/";
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

    public void createChat(Chat chat, String user1Id, String user2Id, Callback_chat<Chat> callback) {
        ChatAPI chatAPI = getAPI();
        Call<Chat> call = chatAPI.createChat(chat, user1Id, user2Id);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to create chat: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                callback.onFailure("Failed to create chat: " + t.getMessage());
            }
        });
    }

    public void getChatByChatId(String chatId, Callback_chat<Chat> callback) {
        ChatAPI chatAPI = getAPI();
        Call<Chat> call = chatAPI.getChatByChatId(chatId);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to find chat: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                callback.onFailure("Failed to find chat: " + t.getMessage());
            }
        });
    }

    public void getChatsByUserId(String userId, Callback_chat<List<Chat>> callback) {
        ChatAPI chatAPI = getAPI();
        Call<List<Chat>> call = chatAPI.getChatsByUserId(userId);
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to find chats: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                callback.onFailure("Failed to find chats: " + t.getMessage());
            }
        });
    }

    public void getChatBetweenUsers(String user1Id, String user2Id, Callback_chat<Chat> callback) {
        ChatAPI chatAPI = getAPI();
        Call<Chat> call = chatAPI.getChatBetweenUsers(user1Id, user2Id);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to find chat: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                callback.onFailure("Failed to find chat: " + t.getMessage());
            }
        });
    }

    public void getAllChats(int size, int page, Callback_chat<List<Chat>> callback) {
        ChatAPI chatAPI = getAPI();
        Call<List<Chat>> call = chatAPI.getAllChats(size, page);
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to find chats: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                callback.onFailure("Failed to find chats: " + t.getMessage());
            }
        });
    }

    public void deleteAll(Callback_chat<Chat> callback) {
        ChatAPI chatAPI = getAPI();
        Call<Chat> call = chatAPI.deleteAll();
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to delete chat: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                callback.onFailure("Failed to delete chat: " + t.getMessage());
            }
        });
    }

}
