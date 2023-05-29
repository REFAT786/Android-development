package com.ibss.contactapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibss.contactapp.R;
import com.ibss.contactapp.domain.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter <UserListAdapter.ViewHolder> {

    private List<User> userList;

    public UserListAdapter(List<User> userList) {
        this.userList = userList;
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
        holder.emailUsersList.setText(currentUser.getEmail());
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
