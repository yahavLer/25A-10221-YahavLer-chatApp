package com.example.a25a_10221_yahavler_chatapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a25a_10221_yahavler_chatapp.R;
import com.example.chatlibrary.model.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<Message> messageList;
    private String currentUserId;

    public MessageAdapter(List<Message> messageList, String currentUserId) {
        this.messageList = messageList;
        this.currentUserId = currentUserId;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        Log.d("MessageAdapter", "Message: " + message.getContent() + ", SenderId: " + message.getSenderId());

        // בדיקה אם ההודעה נשלחה ע"י המשתמש המחובר
        if (message.getSenderId().equals(currentUserId)) {
            holder.tvSenderName.setText("You");
            holder.tvMessageContent.setBackgroundResource(R.drawable.bubble_sent); // עיצוב להודעה שנשלחה ע"י המשתמש
        } else {
            holder.tvSenderName.setText("Other");
            holder.tvMessageContent.setBackgroundResource(R.drawable.bubble_received); // עיצוב להודעה שהתקבלה
        }

        holder.tvMessageContent.setText(message.getContent());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvSenderName, tvMessageContent;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSenderName = itemView.findViewById(R.id.tvSenderName);
            tvMessageContent = itemView.findViewById(R.id.tvMessageContent);
        }
    }
}
