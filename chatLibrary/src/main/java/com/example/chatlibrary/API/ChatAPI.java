package com.example.chatlibrary.API;


import com.example.chatlibrary.model.Chat;
import com.example.chatlibrary.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ChatAPI {
    @POST("api/chats/create")
    Call<Chat> createChat(@Body Chat chat, @Query("user1Id") String user1Id, @Query("user2Id") String user2Id);

    @GET("api/chats/chat/{chatId}")
    Call<Chat> getChatByChatId(@Path("chatId") String chatId);

    @GET("api/chats/user/{userId}")
    Call<List<Chat>> getChatsByUserId(@Path("userId") String userId);

    @GET("api/chats/user1/{user1Id}/user2/{user2Id}")
    Call<Chat> getChatBetweenUsers(@Path("user1Id") String user1Id, @Path("user2Id") String user2Id);

    @GET("api/chats/all")
    Call<List<Chat>> getAllChats(@Query("size") int size, @Query("page") int page);

    @DELETE("api/chats/delete")
    Call<Chat> deleteAll();
}
