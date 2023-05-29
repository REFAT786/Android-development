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
import android.widget.Button;
import android.widget.Toast;

import com.example.crudapp.adapter.Contact_Custom_Adapter;
import com.example.crudapp.dao.OnDelete;
import com.example.crudapp.dao.OnUpdate;
import com.example.crudapp.domain.ContactItems;
import com.example.crudapp.model.ContactViewModel;

public class MainActivity extends AppCompatActivity implements OnDelete, OnUpdate {
    ContactViewModel viewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button addButton = findViewById(R.id.add_button_main_id);
        Button deleteButton = findViewById(R.id.delete_button_id);

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

        deleteButton.setOnClickListener(view -> viewModel.deleteAllContacts());


    }
    public void onDelete(ContactItems contactItems){viewModel.delete(contactItems);}
    public void onUpdate(ContactItems contactItems){
        int s = contactItems.getId();
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        intent.putExtra("com.example.crudapp.id", s);
        activityResultLauncher.launch(intent);
        //viewModel.update(contactItems);

    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            int res = result.getResultCode();

            if(res == 1){
                Intent intent = result.getData();
                if(intent != null){
                    int n0 = intent.getIntExtra("id", 0);
                    String n1 = intent.getStringExtra("name");
                    String n2 = intent.getStringExtra("email");
                    String n3 = intent.getStringExtra("phone");

                    ContactItems contactItems = new ContactItems(n0,n1,n2,n3);
                    viewModel.update(contactItems);

                    Toast.makeText(MainActivity.this, "Update", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }


        }
    });

}