package com.ibss.ibss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ibss.ibss.R;
import com.ibss.ibss.dao.UserListSelectListener;
import com.ibss.ibss.domain.User;

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

        holder.nameUsersList.setText(currentUser.getName());
        holder.emailUsersList.setText(currentUser.getGender());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(currentUser));

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView emailUsersList,nameUsersList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.nameUsersList = itemView.findViewById(R.id.name_id);
            this.emailUsersList = itemView.findViewById(R.id.mail_id);

        }
    }

}
