package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SecondActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        TaskItem[] taskItems = new TaskItem[] {
                new TaskItem("Birthday", "Nasim", "12-12-2023", "12:30 am", "active"),
                new TaskItem("Joinday", "Rafi", "12-12-2023", "10:30 am", "inactive")
        };

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        CustomAdapter adapter = new CustomAdapter(taskItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //.............

        ImageButton nextbutton = findViewById(R.id.next_button_id);

        nextbutton.setOnClickListener(view -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}