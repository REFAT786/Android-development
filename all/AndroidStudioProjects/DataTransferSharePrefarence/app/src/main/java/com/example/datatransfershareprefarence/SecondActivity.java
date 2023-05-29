package com.example.datatransfershareprefarence;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.text_view_id);
        editText =findViewById(R.id.edit_text_id);
        button = findViewById(R.id.button_id);

        sharedPreferences = getSharedPreferences("com.example.datatransfershareprefarence",  Context.MODE_PRIVATE);
        String v = String.valueOf(sharedPreferences.getInt("value", 0));

        textView.setText(v);

    }
}