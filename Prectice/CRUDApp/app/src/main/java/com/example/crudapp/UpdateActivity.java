package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        EditText name = findViewById(R.id.name_update_editView_id);
        EditText email = findViewById(R.id.email_update_editView_id);
        EditText phone = findViewById(R.id.phone_update_editView_id);

        Button update = findViewById(R.id.update_button_id);

        //................getin start
        Bundle bundle = getIntent().getExtras();

        int n = bundle.getInt("com.example.crudapp.id");

        name.setText(String.valueOf(n));
        //get in finish

        //getout start
        update.setOnClickListener(view -> {

            String updateName = name.getText().toString();
            String updateEmail = email.getText().toString();
            String updatePhone = phone.getText().toString();

            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
            intent.putExtra("com.example.crudapp.name", updateName);
            intent.putExtra("com.example.crudapp.email", updateEmail);
            intent.putExtra("com.example.crudapp.phone", updatePhone);
            setResult(1, intent);
            finish();

        });
    }
}