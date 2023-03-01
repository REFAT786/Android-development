package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appdesign.domain.Users;
import com.example.appdesign.model.UsersViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    public TextInputEditText fullname,username,password,email,phone;
    public Button signup, moveSignin;

    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullname = findViewById(R.id.fullname_id);
        username = findViewById(R.id.username_id);
        password = findViewById(R.id.password_id);
        email = findViewById(R.id.password_id);
        phone = findViewById(R.id.phone_id);
        signup = findViewById(R.id.signupButton_id);

        UsersViewModel viewModel = new ViewModelProvider(this).get(UsersViewModel.class);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f_name = fullname.getText().toString();
                String u_name = username.getText().toString();
                String pass = password.getText().toString();
                String e_mail = email.getText().toString();
                String phn = phone.getText().toString();

                if (fullname.getText().length()<=0) {
                    Toast.makeText(SignupActivity.this, "Enter your Fullname", Toast.LENGTH_SHORT).show();

                }
                else if (username.getText().length()<=0) {
                    Toast.makeText(SignupActivity.this, "Enter your Username", Toast.LENGTH_SHORT).show();

                }
                else if (password.getText().length()<=0) {
                    Toast.makeText(SignupActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();

                }
                else if (email.getText().length()<=0) {
                    Toast.makeText(SignupActivity.this, "Enter your email", Toast.LENGTH_SHORT).show();

                }
                else if (phone.getText().length()<=0) {
                    Toast.makeText(SignupActivity.this, "Enter your phone number", Toast.LENGTH_SHORT).show();

                }
                else{
                    Users users = new Users(f_name,u_name,pass,e_mail,phn);
                    viewModel.insert(users);

                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


        moveSignin = findViewById(R.id.moveSignin_id);

        moveSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}