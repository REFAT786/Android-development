package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_one = findViewById(R.id.button_one_id);
        Button button_two = findViewById(R.id.button_two_id);
        Button button_three = findViewById(R.id.button_three_id);

        loadFrag(new OneFragment(), 0);

        button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new OneFragment(), 1);
                Toast.makeText(MainActivity.this, "one", Toast.LENGTH_SHORT).show();
            }
        });
        button_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new TwoFragment(), 1);
                Toast.makeText(MainActivity.this, "Two", Toast.LENGTH_SHORT).show();

            }
        });
        button_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFrag(new ThreeFragment(), 1);
                Toast.makeText(MainActivity.this, "Three", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void loadFrag(Fragment fragment, int flag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack("reverse");

        if(flag == 0){
            ft.add(R.id.contain, fragment);
        }
        else {
            ft.replace(R.id.contain, fragment);
        }

        ft.commit();
    }

}