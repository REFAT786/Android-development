package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.example.crudapp.domain.ContactItems;
import com.example.crudapp.model.ContactViewModel;

public class UpdateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        EditText name = findViewById(R.id.name_update_editView_id);
        EditText email = findViewById(R.id.email_update_editView_id);
        EditText phone = findViewById(R.id.phone_update_editView_id);
        Button update = findViewById(R.id.update_button_id);

        ContactViewModel viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        Bundle bundle = getIntent().getExtras();
        int n = bundle.getInt("com.example.crudapp.id");

        viewModel.loadSingle(n).observe(this, contactItems -> {
            name.setText(contactItems.getName());
            email.setText(contactItems.getEmail());
            phone.setText(contactItems.getPhone());

        });

        update.setOnClickListener(view -> {

            String updateName  = name.getText().toString();
            String updateEmail = email.getText().toString();
            String updatePhone = phone.getText().toString();

            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
            intent.putExtra("name", updateName);
            intent.putExtra("email",updateEmail);
            intent.putExtra("phone", updatePhone);
            setResult(1,intent);
            finish();

            //ContactItems contactItems = new ContactItems(updateName,updateEmail,updatePhone);
            //viewModel.update(contactItems);


        });

    }
}