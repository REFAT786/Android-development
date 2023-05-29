package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appdesign.model.UsersViewModel;

public class WelcomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView textView;
    public Button logoutButton;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sharedPreferences = getSharedPreferences("com.example.appdesign.database", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        textView = findViewById(R.id.textView);
        logoutButton = findViewById(R.id.logoutButton_id);

        UsersViewModel viewModel = new ViewModelProvider(this).get(UsersViewModel.class);


        String username = sharedPreferences.getString("username","");
        String full_name = sharedPreferences.getString("full_name","");
        textView.setText(full_name + " + " + username );


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