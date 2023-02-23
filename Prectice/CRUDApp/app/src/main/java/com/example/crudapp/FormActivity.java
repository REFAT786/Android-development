package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.crudapp.domain.ContactItems;
import com.example.crudapp.model.ContactViewModel;

public class FormActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        EditText name = findViewById(R.id.name_editView_id);
        EditText email = findViewById(R.id.email_editView_id);
        EditText phone = findViewById(R.id.phone_editView_id);
        Button add = findViewById(R.id.add_button_id);

        ContactViewModel viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userPhone = phone.getText().toString();

                ContactItems contactItems = new ContactItems(userName,userEmail,userPhone);
                viewModel.insert(contactItems);
                Intent intent = new Intent(FormActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}