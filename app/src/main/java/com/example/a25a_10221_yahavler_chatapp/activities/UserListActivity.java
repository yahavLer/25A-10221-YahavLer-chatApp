package com.example.a25a_10221_yahavler_chatapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a25a_10221_yahavler_chatapp.adapters.UserAdapter;
import com.example.a25a_10221_yahavler_chatapp.R;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.chatSDK;
import com.example.chatlibrary.model.Chat;
import com.example.chatlibrary.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private String currentUserId;
    private RecyclerView recyclerUserList;
    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerUserList = findViewById(R.id.recycler_user_list);
        recyclerUserList.setLayoutManager(new LinearLayoutManager(this));

        currentUserId = getIntent().getStringExtra("currentUserId");
        if (currentUserId == null || currentUserId.isEmpty()) {
            Toast.makeText(this, "Error: No user ID provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        userAdapter = new UserAdapter(userList, this::createChatWithUser);
        recyclerUserList.setAdapter(userAdapter);
        loadUsers();
    }

    private void loadUsers() {
        chatSDK.getAllUsers(30,0, new Callback_chat<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                runOnUiThread(() -> {
                    userList.clear();
                    for (User user : users) {
                        if (!user.getId().equals(currentUserId)) {
                            userList.add(user);
                        }
                    }
                    userAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Toast.makeText(UserListActivity.this, "Error loading users: " + errorMessage, Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void createChatWithUser(User user) {
        chatSDK.createChat(new Chat(), currentUserId, user.getId(), new Callback_chat<Chat>() {
            @Override
            public void onSuccess(Chat chat) {
                runOnUiThread(() -> {
                    Toast.makeText(UserListActivity.this, "Chat created with " + user.getUsername(), Toast.LENGTH_SHORT).show();
                    finish();  // חזרה למסך הצ'אטים
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Toast.makeText(UserListActivity.this, "Failed to create chat: " + errorMessage, Toast.LENGTH_SHORT).show());
            }
        });
    }
}
