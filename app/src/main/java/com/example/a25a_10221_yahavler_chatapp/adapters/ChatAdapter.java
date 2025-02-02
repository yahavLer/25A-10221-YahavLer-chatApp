package com.example.a25a_10221_yahavler_chatapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a25a_10221_yahavler_chatapp.R;
import com.example.a25a_10221_yahavler_chatapp.activities.ChatActivity;
import com.example.chatlibrary.Callback_chat;
import com.example.chatlibrary.chatSDK;
import com.example.chatlibrary.model.Chat;
import com.example.chatlibrary.model.Message;
import com.example.chatlibrary.model.User;
import java.util.List;
import java.util.Objects;

import com.example.a25a_10221_yahavler_chatapp.ChatCallback;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<Chat> chatList;
    private OnChatClickListener listener;
    private String currentUserId;
    private ChatCallback chatCallback;

    public interface OnChatClickListener {
        void onChatClick(Chat chat);
    }

    public ChatAdapter(List<Chat> chatList, String currentUserId, OnChatClickListener listener) {
        this.chatList = chatList;
        this.listener = listener;
        this.currentUserId = currentUserId; // עכשיו זה מתקבל מה-Intent
    }

    public void setChatCallback(ChatCallback chatCallback) {
        this.chatCallback = chatCallback;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = chatList.get(position);
//        String otherUserId = chat.getUser1Id().equals(currentUserId) ? chat.getUser2Id() : chat.getUser1Id();
        String otherUserId = Objects.equals(chat.getUser1Id(), currentUserId) ? chat.getUser2Id() : chat.getUser1Id();

        chatSDK.getUserByUserId(otherUserId, new Callback_chat<User>() {
            @Override
            public void onSuccess(User result) {
                holder.tvChatName.setText("Chat with: " + result.getUsername());
            }

            @Override
            public void onFailure(String errorMessage) {
                holder.tvChatName.setText("Chat with: Unknown");
            }
        });

        // השגת ההודעה האחרונה מתוך רשימת ההודעות
        List<Message> messages = chat.getMessages();
        if (messages != null && !messages.isEmpty()) {
            Message lastMessage = messages.get(messages.size() - 1);
            holder.tvLastMessage.setText(lastMessage.getContent());
        } else {
            holder.tvLastMessage.setText("No messages yet");
        }

        // לחיצה על הצ'אט תפתח את השיחה
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ChatActivity.class);
            intent.putExtra("chatId", chat.getId());  // שליחת chatId
            intent.putExtra("currentUserId", currentUserId);  // שליחת currentUserId
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvChatName, tvLastMessage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChatName = itemView.findViewById(R.id.tvChatName);
            tvLastMessage = itemView.findViewById(R.id.tvLastMessage);
        }
    }
}
