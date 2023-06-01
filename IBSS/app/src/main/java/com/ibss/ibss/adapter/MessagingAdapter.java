package com.ibss.ibss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ibss.ibss.R;
import com.ibss.ibss.domain.Chat;

import java.util.ArrayList;

public class MessagingAdapter extends RecyclerView.Adapter<MessagingAdapter.ViewHolder> {

    int msg_right = 1;
    int msg_left = 0;
    ArrayList<Chat> chatList;
    Context context;
    FirebaseUser firebaseUser;

    public MessagingAdapter(ArrayList<Chat> chatList, Context context) {
        this.chatList = chatList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if(viewType == msg_right){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);

        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);

        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Chat chat = chatList.get(position);

        holder.show_msg_right.setText(chat.getMsg());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView show_msg_right;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_msg_right = itemView.findViewById(R.id.show_msg_id);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(chatList.get(position).getSender().equals(firebaseUser.getUid())){
            return msg_right;
        }
        else {
            return msg_left;
        }
    }
}
