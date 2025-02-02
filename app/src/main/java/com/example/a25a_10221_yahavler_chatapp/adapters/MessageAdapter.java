package com.example.a25a_10221_yahavler_chatapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a25a_10221_yahavler_chatapp.R;
import com.example.chatlibrary.model.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<Message> messageList;
    private String currentUserId;
    private String otherUserName;

    public MessageAdapter(List<Message> messageList, String currentUserId,String otherUserName) {
        this.messageList = messageList;
        this.currentUserId = currentUserId;
        this.otherUserName = otherUserName;
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
        Log.d("MessageAdapter", "Other user name: " + otherUserName);
        // בדיקה אם ההודעה נשלחה ע"י המשתמש המחובר
        if (message.getSenderId().equals(currentUserId)) {
            holder.tvSenderName.setText("You");
            holder.tvTime.setText(message.getTimestamp());
            holder.tvMessageContent.setBackgroundResource(R.drawable.bubble_sent); // עיצוב להודעה שנשלחה ע"י המשתמש
            ((LinearLayout) holder.itemView).setGravity(Gravity.END);  // יישור לימין
        } else {
            holder.tvSenderName.setText(otherUserName);
            holder.tvTime.setText(message.getTimestamp());
            holder.tvMessageContent.setBackgroundResource(R.drawable.bubble_received); // עיצוב להודעה שהתקבלה
            ((LinearLayout) holder.itemView).setGravity(Gravity.START);  // יישור לשמאל
        }

        holder.tvMessageContent.setText(message.getContent());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvSenderName, tvMessageContent, tvTime;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSenderName = itemView.findViewById(R.id.tvSenderName);
            tvMessageContent = itemView.findViewById(R.id.tvMessageContent);
            tvTime=itemView.findViewById(R.id.tvTime);
        }
    }
}
