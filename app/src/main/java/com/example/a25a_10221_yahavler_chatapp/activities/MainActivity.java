package com.example.a25a_10221_yahavler_chatapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a25a_10221_yahavler_chatapp.R;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.chatSDK;
import com.example.chatlibrary.model.Message;
import com.example.chatlibrary.model.User;
import com.example.chatlibrary.model.Message;

public class MainActivity extends AppCompatActivity {
    private User currentUser;
    EditText etUserName;
    EditText etPhoneNumber;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        btnLogin.setOnClickListener(view -> loginUser());
        btnRegister.setOnClickListener(view -> registerUser());
    }

    private void loginUser() {
        String username = etUserName.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        chatSDK.getUserByUserId(username, new Callback_chat<User>() {
            @Override
            public void onSuccess(User result) {
                currentUser = result;
                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                openChatList();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(MainActivity.this, "User not found. Please register.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser() {
        String username = etUserName.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
            return;
        }


        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPhoneNumber(phoneNumber);
        chatSDK.createUser(newUser, new Callback_chat<User>() {
            @Override
            public void onSuccess(User result) {
                currentUser = result;
                Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                openChatList();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(MainActivity.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openChatList() {
        Intent intent = new Intent(MainActivity.this, ChatListActivity.class);
        startActivity(intent);
        finish();  // מסיים את המסך כדי שלא יוכל לחזור אחורה
    }

    private void findViews() {
        etUserName = findViewById(R.id.etUsername);
        etPhoneNumber = findViewById(R.id.etPhone);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);


    }
}