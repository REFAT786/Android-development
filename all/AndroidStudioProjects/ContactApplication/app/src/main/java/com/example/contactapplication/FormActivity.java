package com.example.contactapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.contactapplication.contact.ContactItem;
import com.example.contactapplication.contact.ContactViewModel;

public class FormActivity extends AppCompatActivity {

    private TextView back;
    private EditText nameInput,emailInput,phoneInput,mobileInput,skypeInput;
    private  Button saveButton;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        back = findViewById(R.id.back_id);
        saveButton = findViewById(R.id.save_id);
        nameInput = findViewById(R.id.name_input_id);
        emailInput = findViewById(R.id.email_input_id);
        phoneInput = findViewById(R.id.phone_input_id);
        mobileInput = findViewById(R.id.mobile_input_id);
        skypeInput = findViewById(R.id.skype_input_id);

        back.setOnClickListener(view -> {
            Intent intent = new Intent(FormActivity.this, MainActivity.class);
            startActivity(intent);
        });

        ContactViewModel viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        saveButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString();
            String email = emailInput.getText().toString();
            String phone = phoneInput.getText().toString();
            String mobile = mobileInput.getText().toString();
            String skype = skypeInput.getText().toString();

            ContactItem contactItem = new ContactItem(name,email,phone,mobile,skype);
            viewModel.insert(contactItem);
            Intent intent = new Intent(FormActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}