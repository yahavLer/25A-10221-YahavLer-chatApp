package com.example.chatlibrary.API;

import com.example.chatlibrary.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserAPI {
    @POST("/api/users/create")
    Call<User> createUser(@Body User user);

    @GET("/api/users/userId/{userId}")
    Call<User> getById(@Path("userId") String userId);

    @GET("/api/users/username/{username}")
    Call<User> getByUsername(@Path("username") String username);

    @GET("/api/users/all")
    Call<List<User>> getAllUsers(@Query("size") int size, @Query("page") int page);
}
