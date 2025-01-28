package com.example.chatlibrary.controllers;

import com.example.chatlibrary.API.UserAPI;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.model.User;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserController {
    private static final String BASE_URL = "http://localhost:8080/api/";

    private UserAPI getAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                                new GsonBuilder()
                                        .setLenient()
                                        .create()
                        )
                )
                .build();
        return retrofit.create(UserAPI.class);
    }

    public void createUser(User user, Callback_chat<User> callback) {
        UserAPI userAPI = getAPI();
        Call<User> call = userAPI.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to create user: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure("Failed to create user: " + t.getMessage());
            }
        });
    }

    public void getById(String userId, Callback_chat<User> callback) {
        UserAPI userAPI = getAPI();
        Call<User> call = userAPI.getById(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to find user: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure("Failed to find user: " + t.getMessage());
            }
        });
    }

    public void getByUsernameAndPhone(String username,String phoneNumber, Callback_chat<User> callback) {
        UserAPI userAPI = getAPI();
        Call<User> call = userAPI.getByUsernameAndPhone(username, phoneNumber);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to find user: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure("Failed to find user: " + t.getMessage());
            }
        });
    }

    public void getAllUsers(int size, int page, Callback_chat<List<User>> callback) {
        UserAPI userAPI = getAPI();
        Call<List<User>> call = userAPI.getAllUsers(size, page);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to find users: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onFailure("Failed to find users: " + t.getMessage());
            }
        });
    }
}
