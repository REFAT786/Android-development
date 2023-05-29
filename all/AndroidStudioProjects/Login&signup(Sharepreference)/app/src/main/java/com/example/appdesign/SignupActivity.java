package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Editor editor;
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

        sharedPreferences = getSharedPreferences("com.example.appdesign", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

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
                    editor.putString("full_name",f_name);
                    editor.putString("user_name",u_name);
                    editor.putString("pass_word",pass);
                    editor.putString("e_mail",e_mail);
                    editor.putString("phone_no",phn);
                    editor.commit();

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