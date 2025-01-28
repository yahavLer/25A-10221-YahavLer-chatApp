package com.example.a25a_10221_yahavler_chatapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a25a_10221_yahavler_chatapp.R;
import com.example.a25a_10221_yahavler_chatapp.adapters.MessageAdapter;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.chatSDK;
import com.example.chatlibrary.model.Chat;
import com.example.chatlibrary.model.Message;
import com.example.chatlibrary.model.User;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerMessages;
    private EditText etMessage;
    private Button btnSend;
    private ImageView btnBack;
    private TextView chatUserTitle;
    private MessageAdapter messageAdapter;
    private List<Message> messageList = new ArrayList<>();
    private String chatId, currentUserId, otherUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerMessages = findViewById(R.id.recycler_view);
        etMessage = findViewById(R.id.edit_message);
        btnSend = findViewById(R.id.btn_send);
        btnBack = findViewById(R.id.btn_back);
        chatUserTitle = findViewById(R.id.chat_user_title);

        recyclerMessages.setLayoutManager(new LinearLayoutManager(this));

        // קבלת הנתונים מה-Intent
        chatId = getIntent().getStringExtra("chatId");
        currentUserId = getIntent().getStringExtra("currentUserId");

        // שליפת המידע של הצ'אט
        loadChatInfo();

        // טעינת ההודעות בצ'אט
        loadMessages();

        btnSend.setOnClickListener(v -> sendMessage());

        // כפתור חזור
        btnBack.setOnClickListener(v -> finish());
    }

    private void loadChatInfo() {
        chatSDK.getChatByChatId(chatId, new Callback_chat<Chat>() {
            @Override
            public void onSuccess(Chat chat) {
                // זיהוי המשתמש השני בצ'אט
                otherUserId = chat.getUser1Id().equals(currentUserId) ? chat.getUser2Id() : chat.getUser1Id();

                // טעינת שם המשתמש השני
                chatSDK.getUserByUserId(otherUserId, new Callback_chat<User>() {
                    @Override
                    public void onSuccess(User user) {
                        runOnUiThread(() -> chatUserTitle.setText("Chat with " + user.getUsername()));
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        runOnUiThread(() -> chatUserTitle.setText("Chat with Unknown"));
                    }
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Toast.makeText(ChatActivity.this, "Failed to load chat info", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void loadMessages() {
        chatSDK.getMessagesByConversationId(chatId, new Callback_chat<List<Message>>() {
            @Override
            public void onSuccess(List<Message> result) {
                runOnUiThread(() -> {
                    messageList.clear();
                    messageList.addAll(result);
                    messageAdapter = new MessageAdapter(messageList, currentUserId);
                    recyclerMessages.setAdapter(messageAdapter);
                    recyclerMessages.scrollToPosition(messageList.size() - 1);
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Toast.makeText(ChatActivity.this, "Error loading messages", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void sendMessage() {
        String content = etMessage.getText().toString().trim();
        if (content.isEmpty()) return;

        Message newMessage = new Message();
        newMessage.setSenderId(currentUserId);
        newMessage.setContent(content);
        newMessage.setReceiverId(otherUserId);
        chatSDK.sendMessage(newMessage, new Callback_chat<Message>() {
            @Override
            public void onSuccess(Message result) {
                runOnUiThread(() -> {
                    messageList.add(result);
                    messageAdapter.notifyItemInserted(messageList.size() - 1);
                    recyclerMessages.scrollToPosition(messageList.size() - 1);
                    etMessage.setText("");
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                runOnUiThread(() -> Toast.makeText(ChatActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show());
            }
        });
    }
}