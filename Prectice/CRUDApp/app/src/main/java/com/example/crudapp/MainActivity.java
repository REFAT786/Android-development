package com.example.crudapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudapp.adapter.Contact_Custom_Adapter;
import com.example.crudapp.dao.OnDelete;
import com.example.crudapp.dao.OnUpdate;
import com.example.crudapp.domain.ContactItems;
import com.example.crudapp.model.ContactViewModel;

public class MainActivity extends AppCompatActivity implements OnDelete, OnUpdate {
    ContactViewModel viewModel;
    private TextView name,email,phone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         name = findViewById(R.id.name_id);
         email = findViewById(R.id.email_id);
         phone = findViewById(R.id.phone_id);


        Button addButton = findViewById(R.id.add_button_main_id);

        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recycle_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        viewModel.getAllContacts().observe(this, contactItems -> {
            Contact_Custom_Adapter adapter = new Contact_Custom_Adapter(contactItems,  MainActivity.this, MainActivity.this);
            recyclerView.setAdapter(adapter);
        });


    }
    public void onDelete(ContactItems contactItems){viewModel.delete(contactItems);}
    public void onUpdate(ContactItems contactItems){
        int s = contactItems.getId();
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        intent.putExtra("com.example.crudapp.id", s);
        activityResultLauncher.launch(intent);

        viewModel.update(contactItems);
    }
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            int res = result.getResultCode();

            if(res == 1){
                Intent intent = result.getData();
                if(intent != null){
                    String nameData = intent.getStringExtra("com.example.crudapp.name");
                    String emailData = intent.getStringExtra("com.example.crudapp.email");
                    String phoneData = intent.getStringExtra("com.example.crudapp.phone");

                    name.setText(nameData);
                    email.setText(emailData);
                    phone.setText(phoneData);

                    Toast.makeText(MainActivity.this, "Data update", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

        }
    });
}