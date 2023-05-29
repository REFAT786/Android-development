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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView daytextid,nametextid,timetextid;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        daytextid = findViewById(R.id.daytextid);
        nametextid = findViewById(R.id.nametextid);
        timetextid = findViewById(R.id.timetextid);

        //We create  object of Bundle class that help to get data from MainActivity and used its  getString method

        Bundle bundle = getIntent().getExtras();
        String catagory = bundle.getString("title");
        String name = bundle.getString("name");
        String time = bundle.getString("time");
        daytextid.setText(String.valueOf(catagory));
        nametextid.setText(String.valueOf(name));
        timetextid.setText(String.valueOf(time));



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