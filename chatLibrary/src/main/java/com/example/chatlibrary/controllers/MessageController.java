package com.example.chatlibrary.controllers;

import com.example.chatlibrary.API.MessageAPI;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.model.Message;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageController {
//    private static final String BASE_URL = "http://localhost:8080/api/";
    private static final String BASE_URL = "https://favourable-lorrie-yahavler-7d646b21.koyeb.app/";

    private MessageAPI getAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                                new GsonBuilder()
                                        .setLenient()
                                        .create()
                        )
                )
                .build();
        return retrofit.create(MessageAPI.class);
    }

    public void sendMessage(Message message, Callback_chat<Message> callback) {
        MessageAPI messageAPI = getAPI();
        Call<Message> call = messageAPI.sendMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to create message: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                callback.onFailure("Failed to create message: " + t.getMessage());
            }
        });
    }

    public void getMessageById(String messageId, Callback_chat<Message> callback) {
        MessageAPI messageAPI = getAPI();
        Call<Message> call = messageAPI.getMessageById(messageId);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to get message: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                callback.onFailure("Failed to get message: " + t.getMessage());
            }
        });
    }

    public void getAllMessages(int size, int page, Callback_chat<List<Message>> callback) {
        MessageAPI messageAPI = getAPI();
        Call<List<Message>> call = messageAPI.getAllMessages(size, page);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to get messages: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                callback.onFailure("Failed to get messages: " + t.getMessage());
            }
        });
    }

    public void deleteAll(Callback_chat<Message> callback) {
        MessageAPI messageAPI = getAPI();
        Call<Message> call = messageAPI.deleteAll();
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to delete messages: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                callback.onFailure("Failed to delete messages: " + t.getMessage());
            }
        });
    }


    public void getMessagesByUserIdToReciverId(String userId, String receiverId, Callback_chat<List<Message>> callback) {
        MessageAPI messageAPI = getAPI();
        Call<List<Message>> call = messageAPI.getMessagesByUserIdToReciverId(userId, receiverId);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to get messages: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                callback.onFailure("Failed to get messages: " + t.getMessage());
            }
        });
    }

    public void getMessagesByUserIdFromSenderId(String userId, String senderId, Callback_chat<List<Message>> callback) {
        MessageAPI messageAPI = getAPI();
        Call<List<Message>> call = messageAPI.getMessagesByUserIdFromSenderId(userId, senderId);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to get messages: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                callback.onFailure("Failed to get messages: " + t.getMessage());
            }
        });
    }

}
