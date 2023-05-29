package com.ibss.contactapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibss.contactapp.R;
import com.ibss.contactapp.dao.UserListSelectListener;
import com.ibss.contactapp.domain.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter <UserListAdapter.ViewHolder> {

    ArrayList<User> userList;
    Context context;
    private final UserListSelectListener listener;

    public UserListAdapter(ArrayList<User> userList, Context context, UserListSelectListener listener) {
        this.userList = userList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User currentUser = userList.get(position);
        holder.emailUsersList.setText(currentUser.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(currentUser));

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView emailUsersList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.emailUsersList = itemView.findViewById(R.id.email_users_list_id);

        }
    }




}
