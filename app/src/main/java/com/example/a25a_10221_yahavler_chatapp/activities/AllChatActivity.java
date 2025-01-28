package com.example.a25a_10221_yahavler_chatapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a25a_10221_yahavler_chatapp.adapters.ChatAdapter;
import com.example.a25a_10221_yahavler_chatapp.R;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.chatSDK;
import com.example.chatlibrary.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class AllChatActivity extends AppCompatActivity {
    private RecyclerView recyclerChatList;
    private ChatAdapter chatAdapter;
    private List<Chat> chatList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chat);
        findViews();
        recyclerChatList.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(chatList, chat -> openChat(chat));
        recyclerChatList.setAdapter(chatAdapter);

        loadChats(); // טוען את הצ'אטים מהשרת
    }
    private void loadChats() {
        chatSDK.getAllChats(10, 1, new Callback_chat<List<Chat>>() {
            @Override
            public void onSuccess(List<Chat> result) {
                runOnUiThread(() -> {
                    chatList.clear();
                    chatList.addAll(result);
                    chatAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Toast.makeText(ChatListActivity.this, "Error loading chats: " + errorMessage, Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void openChat(Chat chat) {
        Intent intent = new Intent(ChatListActivity.this, ChatActivity.class);
        intent.putExtra("chatId", chat.getChatId());
        startActivity(intent);
    }
    private void findViews() {
        recyclerChatList = findViewById(R.id.recycler_chat_list);
    }
}