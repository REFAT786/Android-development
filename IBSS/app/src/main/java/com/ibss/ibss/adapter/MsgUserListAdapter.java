package com.ibss.ibss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibss.ibss.R;
import com.ibss.ibss.dao.UserListSelectListener;
import com.ibss.ibss.domain.User;

import java.util.ArrayList;

public class MsgUserListAdapter extends RecyclerView.Adapter <MsgUserListAdapter.ViewHolder>{

    ArrayList<User> userList;
    Context context;
    private final UserListSelectListener listener;

    public MsgUserListAdapter(ArrayList<User> userList, Context context, UserListSelectListener listener) {
        this.userList = userList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MsgUserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_list, parent, false);
        return new MsgUserListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgUserListAdapter.ViewHolder holder, int position) {

        User currentUser = userList.get(position);

        holder.nameUsersList.setText(currentUser.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(currentUser));

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameUsersList;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.nameUsersList = itemView.findViewById(R.id.name_msg_id);
            this.imageView = itemView.findViewById(R.id.profile_image_msg_id);

        }
    }

}

