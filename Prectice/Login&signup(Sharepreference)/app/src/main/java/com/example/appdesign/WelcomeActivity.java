package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView textView;
    public Button logoutButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textView = findViewById(R.id.textView);
        logoutButton = findViewById(R.id.logoutButton_id);

        sharedPreferences = getSharedPreferences("com.example.appdesign", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String name = sharedPreferences.getString("full_name","");
        textView.setText(name);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putBoolean("isLoggedIn",false);
                editor.commit();

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        });
    }
}