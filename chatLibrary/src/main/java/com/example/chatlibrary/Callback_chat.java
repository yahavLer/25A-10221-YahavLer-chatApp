package com.example.chatlibrary;

public interface Callback_chat<T> {
    void onSuccess(T result);
    void onFailure(String errorMessage);
}
