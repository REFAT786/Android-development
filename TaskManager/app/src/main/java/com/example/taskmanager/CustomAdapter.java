package com.example.taskmanager;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.daytextid.setText(currentTaskItem.getCategory());
        holder.nametextid.setText(currentTaskItem.getName());
        holder.timetextid.setText(currentTaskItem.getTime());
        holder.switchid.setChecked(currentTaskItem.getStatus().equals("active"));
    }

    @Override
    public int getItemCount() {
        return taskItems.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView daytextid, nametextid, timetextid;
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.daytextid = itemView.findViewById(R.id.daytextid);
            this.nametextid = itemView.findViewById(R.id.nametextid);
            this.timetextid = itemView.findViewById(R.id.timetextid);
            this.switchid = itemView.findViewById(R.id.switchid);
        }
    }
}

