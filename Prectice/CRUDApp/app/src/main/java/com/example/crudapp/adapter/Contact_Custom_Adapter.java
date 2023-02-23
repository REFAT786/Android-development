package com.example.crudapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudapp.R;
import com.example.crudapp.dao.OnDelete;
import com.example.crudapp.dao.OnUpdate;
import com.example.crudapp.domain.ContactItems;

import java.util.List;

public class Contact_Custom_Adapter extends RecyclerView.Adapter<Contact_Custom_Adapter.ViewHolder>{

    List<ContactItems> contactItems;
    OnDelete onDelete;
    OnUpdate onUpdate;

    public Contact_Custom_Adapter(List<ContactItems> contactItems, OnDelete onDelete, OnUpdate onUpdate) {
        this.contactItems = contactItems;
        this.onDelete = onDelete;
        this.onUpdate = onUpdate;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,email,phone;
        ConstraintLayout each_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.name_id);
            this.email = itemView.findViewById(R.id.email_id);
            this.phone = itemView.findViewById(R.id.phone_id);
            this.each_item = itemView.findViewById(R.id.each_item_id);
        }
    }

    @NonNull
    @Override
    public Contact_Custom_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Contact_Custom_Adapter.ViewHolder holder, int position) {
        ContactItems currentItem = contactItems.get(position);

        holder.name.setText(currentItem.getName());
        holder.email.setText(currentItem.getEmail());
        holder.phone.setText(currentItem.getPhone());
        holder.each_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onDelete.onDelete(contactItems.get(holder.getAdapterPosition()));
                return true;
            }
        });
        holder.each_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpdate.onUpdate(contactItems.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactItems.size();
    }


}
