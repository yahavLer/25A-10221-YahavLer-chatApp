package com.example.a25a_10221_yahavler_chatapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a25a_10221_yahavler_chatapp.R;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.chatSDK;
import com.example.chatlibrary.model.User;

public class MainActivity extends AppCompatActivity {
    private User currentUser;
    String currentUserId;
    EditText etUserName;
    EditText etPhoneNumber;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        btnStart.setOnClickListener(view -> start());
    }


    private void start() {
        String username = etUserName.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        Log.e("USER DETAILS","etUserName" + username +"phoneNumber"+ phoneNumber);

        if (username.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        chatSDK.getByUsernameAndPhone(username, phoneNumber, new Callback_chat<User>() {
            @Override
            public void onSuccess(User result) {

                currentUser = result;
                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                openChatList();
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("USER NOT FOUND","ERROR" + errorMessage);
                createNewUser(username, phoneNumber);
            }
        });
    }

    private void createNewUser(String username, String phoneNumber) {
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
        currentUserId = currentUser.getId();
        Intent intent = new Intent(MainActivity.this, AllChatActivity.class);
        intent.putExtra("currentUserId", currentUserId); // שולחים את ה-UserId למסך הבא
        startActivity(intent);
        finish();  // מסיים את המסך כדי שלא יוכל לחזור אחורה
    }

    private void findViews() {
        etUserName = findViewById(R.id.etUsername);
        etPhoneNumber = findViewById(R.id.etPhone);
        btnStart = findViewById(R.id.btnStart);


    }
}