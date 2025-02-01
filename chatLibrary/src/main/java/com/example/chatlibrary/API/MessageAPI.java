package com.example.chatlibrary.API;

import com.example.chatlibrary.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MessageAPI {
    @POST("api/messages/send")
    Call<Message> sendMessage(@Body Message message);

    @GET("api/messages/chat/{chatId}")
    Call<List<Message>> getMessagesByChatId(@Path("chatId") String chatId);

    @GET("api/messages/user/{userId}/receiver/{receiverId}")
    Call<List<Message>> getMessagesByUserIdToReciverId(
            @Path("userId") String userId, @Path("receiverId") String receiverId);

    @GET("api/messages/user/{userId}/sender/{senderId}")
    Call<List<Message>> getMessagesByUserIdFromSenderId(
            @Path("userId") String userId, @Path("senderId") String senderId);

    @GET("api/messages/{messageId}")
    Call<Message> getMessageById(@Path("messageId") String messageId);

    @GET("api/messages/all")
    Call<List<Message>> getAllMessages(
            @Query("size") int size,
            @Query("page") int page);

    @GET("api/messages/delete")
    Call<Message> deleteAll();
}
