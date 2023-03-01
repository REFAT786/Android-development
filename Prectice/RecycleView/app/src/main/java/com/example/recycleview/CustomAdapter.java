package com.example.recycleview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.ViewHolder>{

    TaskItem[] taskItems;

    public CustomAdapter(TaskItem[] taskItems) {
        this.taskItems = taskItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskItem currentTaskItem = taskItems[position];
        holder.imageView.setImageAlpha(currentTaskItem.getImage());
        holder.textView.setText(currentTaskItem.getText());
    }

    @Override
    public int getItemCount() {
        return taskItems.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        @SuppressLint("UseSwitchCompatOrMaterialCode")

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageId);
            this.textView = itemView.findViewById(R.id.textId);
        }
    }
}

