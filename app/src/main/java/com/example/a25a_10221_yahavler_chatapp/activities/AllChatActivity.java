package com.example.a25a_10221_yahavler_chatapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a25a_10221_yahavler_chatapp.adapters.ChatAdapter;
import com.example.a25a_10221_yahavler_chatapp.R;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.chatSDK;
import com.example.chatlibrary.model.Chat;
import com.example.chatlibrary.model.User;

import java.util.ArrayList;
import java.util.List;

public class AllChatActivity extends AppCompatActivity {
    private String currentUserId;
    private RecyclerView recyclerChatList;
    private ChatAdapter chatAdapter;
    private TextView tvChatListTitle;
    private List<Chat> chatList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chat);
        findViews();

        currentUserId = getIntent().getStringExtra("currentUserId");
        if (currentUserId == null || currentUserId.isEmpty()) {
            Toast.makeText(this, "Error: No user ID provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        loadCurrentUser();
        recyclerChatList.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(chatList, currentUserId, this::openChat);
        recyclerChatList.setAdapter(chatAdapter);
        loadChats();
    }

    private void loadChats() {
        chatSDK.getChatsByUserId(currentUserId, new Callback_chat<List<Chat>>() {
            @Override
            public void onSuccess(List<Chat> chats) {
                runOnUiThread(() -> {
                    chatList.clear();
                    chatList.addAll(chats);
                    chatAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Toast.makeText(AllChatActivity.this, "Error loading chats: " + errorMessage, Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void loadCurrentUser() {
        chatSDK.getUserByUserId(currentUserId, new Callback_chat<User>() {
            @Override
            public void onSuccess(User result) {
                runOnUiThread(() -> tvChatListTitle.setText("Welcome, " + result.getUsername()));
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Toast.makeText(AllChatActivity.this, "Error loading user: " + errorMessage, Toast.LENGTH_SHORT).show());
            }
        });
    }
    private void openChat(Chat chat) {
        Intent intent = new Intent(AllChatActivity.this, ChatActivity.class);
        intent.putExtra("chatId", chat.getId());
        intent.putExtra("currentUserId", currentUserId);
        startActivity(intent);
    }
    private void findViews() {
        recyclerChatList = findViewById(R.id.recycler_chat_list);
        tvChatListTitle = findViewById(R.id.tvChatListTitle);
    }

}