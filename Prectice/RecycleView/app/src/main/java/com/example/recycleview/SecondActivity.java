package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageView = findViewById(R.id.imageId);
        textView = findViewById(R.id.textId);



        TaskItem[] taskItems = new TaskItem[] {
                new TaskItem(R.drawable.human1, "friend request 1"),
                new TaskItem(R.drawable.human2, "friend request 2"),
                new TaskItem(R.drawable.human3, "friend request 3"),
                new TaskItem(R.drawable.human4, "friend request 4")
        };

        RecyclerView recyclerView = findViewById(R.id.recycler_view_id);
        CustomAdapter adapter = new CustomAdapter(taskItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}