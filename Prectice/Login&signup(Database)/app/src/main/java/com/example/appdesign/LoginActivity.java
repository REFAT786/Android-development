package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.appdesign.domain.Users;
import com.example.appdesign.model.UsersViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public TextInputEditText loginUsername,loginPasswordName;
    public Button moveSignup, loginButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("com.example.appdesign.database", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loginUsername = findViewById(R.id.loginUserName_id);
        loginPasswordName = findViewById(R.id.loginPassword_id);

        UsersViewModel viewModel = new ViewModelProvider(this).get(UsersViewModel.class);

        loginButton = findViewById(R.id.loginButton_id);

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(this, WelcomeActivity.class));
        }

        loginButton.setOnClickListener(view -> {

            String login_user = loginUsername.getText().toString();
            String login_pass = loginPasswordName.getText().toString();


                if(login_user.trim().length() > 0 && login_pass.trim().length() > 0 ){
                    viewModel.findByUsername(login_user).observe(this, new Observer<Users>() {
                        @Override
                        public void onChanged(Users users) {

                            String full_name = users.getFullname();

                            if (login_pass.equals(users.getPassword())) {

                                editor.putBoolean("isLoggedIn", true);
                                editor.putString("full_name",full_name);
                                editor.putString("username",login_user);
                                editor.putString("password",login_pass);
                                editor.commit();

                                Toast.makeText(LoginActivity.this, "done", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                startActivity(intent);
                            }
                            else {
                                setContentView(R.layout.activity_login);
                                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
            });

        moveSignup = findViewById(R.id.moveSignup_id);

        moveSignup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}