package com.example.a25a_10221_yahavler_chatapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AllChatActivity extends AppCompatActivity {
    private RecyclerView recyclerChatList;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chat);
        findViews();
    }

    private void findViews() {
        recyclerChatList = findViewById(R.id.recycler_chat_list);
    }
}