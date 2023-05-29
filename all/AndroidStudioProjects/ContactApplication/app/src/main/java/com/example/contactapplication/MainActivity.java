package com.example.contactapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.contactapplication.adapter.CustomAdapter;
import com.example.contactapplication.contact.ContactItem;
import com.example.contactapplication.contact.ContactViewModel;
import com.example.contactapplication.contact.OnDelete;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDelete {

    private TextView name,email,phone,mobile,skype;
    private Button addButton;

    ContactViewModel viewModel;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name_id);
        email = findViewById(R.id.email_id);
        phone = findViewById(R.id.phone_id);
        mobile = findViewById(R.id.mobile_id);
        skype = findViewById(R.id.skype_id);
        addButton = findViewById(R.id.add_button_id);

        RecyclerView recyclerView = findViewById(R.id.recycle_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        viewModel.getAllContacts().observe(this, contactItems -> {
            CustomAdapter adapter = new CustomAdapter(contactItems, MainActivity.this);
            recyclerView.setAdapter(adapter);
        });


        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onDelete(ContactItem contactItem) {
        viewModel.delete(contactItem);
    }
}