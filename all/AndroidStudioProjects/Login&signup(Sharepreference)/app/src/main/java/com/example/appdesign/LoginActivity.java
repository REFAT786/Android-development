package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public TextInputEditText loginUsername,loginPasswordName;
    public Button moveSignup, loginButton;


    //UserSession session;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("com.example.appdesign", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loginUsername = findViewById(R.id.loginUserName_id);
        loginPasswordName = findViewById(R.id.loginPassword_id);

        loginButton = findViewById(R.id.loginButton_id);

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(this, WelcomeActivity.class));
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login_user = loginUsername.getText().toString();
                String login_pass = loginPasswordName.getText().toString();



                    if(login_user.trim().length() > 0 && login_pass.trim().length() > 0 ){
                        String name = sharedPreferences.getString("user_name","");
                        String password = sharedPreferences.getString("pass_word","");

                        if(login_user.equals(name) && login_pass.equals(password)){
                            Toast.makeText(LoginActivity.this, "done", Toast.LENGTH_SHORT).show();
                            editor.putBoolean("isLoggedIn", true);
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            setContentView(R.layout.activity_login);
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                    }
                }
        });

        moveSignup = findViewById(R.id.moveSignup_id);

        moveSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}