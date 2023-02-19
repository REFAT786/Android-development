package com.example.datatransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.text_view_id);
        Button button = findViewById(R.id.button_id);
        editText = findViewById(R.id.edit_text_id);

        Bundle bundle = getIntent().getExtras();

        String n = String.valueOf(bundle.getInt("key"));

        textView.setText(n);




        button.setOnClickListener(view -> {

            int num2 = Integer.parseInt(String.valueOf(editText.getText()));

            String n2 = textView.getText().toString();
            int i = Integer.parseInt(n2);
            int sum = i+num2;
            String s = String.valueOf(sum);

            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            intent.putExtra("com.example.datatransfer", s);
            setResult(1, intent);
            finish();

        });



    }
}