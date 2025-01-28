package com.example.a25a_10221_yahavler_chatapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a25a_10221_yahavler_chatapp.adapters.MessageAdapter;
import com.example.a25a_10221_yahavler_chatapp.R;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editMessage;
    private Button btnSend;
    private MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViews();

    }

    private void findViews() {
        recyclerView = findViewById(R.id.recycler_chat_list);
        editMessage = findViewById(R.id.edit_message);
        btnSend = findViewById(R.id.btn_send);
    }
}