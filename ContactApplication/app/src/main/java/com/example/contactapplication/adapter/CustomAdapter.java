package com.example.contactapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapplication.R;
import com.example.contactapplication.contact.ContactItem;
import com.example.contactapplication.contact.OnDelete;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.ViewHolder> {

//    ContactItem[] contactItems;contactItems
    private List<ContactItem> contactItems;
    OnDelete onDelete;

    public CustomAdapter(List<ContactItem> contactItems, OnDelete onDelete) {
        this.contactItems = contactItems;
        this.onDelete = onDelete;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name,email,phone,mobile,skype;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.name_id);
            this.email = itemView.findViewById(R.id.email_id);
            this.phone = itemView.findViewById(R.id.phone_id);
            this.mobile = itemView.findViewById(R.id.mobile_id);
            this.skype = itemView.findViewById(R.id.skype_id);
            this.cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        ContactItem currentContactItem = contactItems.get(position);

        holder.name.setText(currentContactItem.getName());
        holder.email.setText(currentContactItem.getEmail());
        holder.phone.setText(currentContactItem.getPhone_no());
        holder.mobile.setText(currentContactItem.getMobile_no());
        holder.skype.setText(currentContactItem.getSkype_id());

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onDelete.onDelete(contactItems.get(holder.getAdapterPosition()));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactItems.size();
    }
}
