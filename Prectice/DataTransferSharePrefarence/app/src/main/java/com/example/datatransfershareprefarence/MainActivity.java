package com.example.datatransfershareprefarence;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView textView;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.example.datatransfershareprefarence",  Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editText = findViewById(R.id.edit_text_id);
        button = findViewById(R.id.button_id);
        textView = findViewById(R.id.text_view_id);

        Intent secondIntent = new Intent(this, SecondActivity.class);

        button.setOnClickListener(view -> {

            String v = editText.getText().toString();
            int value = Integer.parseInt(v);

            //editor.putString("value", value);
            editor.putInt("value",value);
            editor.apply();

            startActivity(secondIntent);

        });
    }
}