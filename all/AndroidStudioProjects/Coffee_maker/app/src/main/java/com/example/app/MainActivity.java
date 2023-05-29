package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int number = 1, milkPrice = 0, sugarPrice = 0,totalPay = 0;
    private TextView num;
    private TextView totalamount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.numid);
        totalamount = findViewById(R.id.totalamountid);

        Spinner milkspinner = findViewById(R.id.milkspinnerid);
        Spinner sugarspinner = findViewById(R.id.sugarspinnerid);

        Button dec = findViewById(R.id.decid);
        Button inc = findViewById(R.id.incid);
        @SuppressLint("CutPasteId") Button make = findViewById(R.id.makeid);

        num.setText(String.valueOf(number));

        ArrayAdapter<String> milkadapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.milk_string));
        milkspinner.setOnItemSelectedListener(this);
        milkspinner.setAdapter(milkadapter);

        ArrayAdapter<String> sugaradapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.sugar_string));
        sugarspinner.setOnItemSelectedListener(this);
        sugarspinner.setAdapter(sugaradapter);

        dec.setOnClickListener(view -> {
            if (number <= 1) {
                number = 1;
            }else{
                number--;
            }
            num.setText(String.valueOf(number));
        });
        inc.setOnClickListener(view -> {
            number++;
            num.setText(String.valueOf(number));
        });

        make.setOnClickListener(view -> {
            totalPay = (milkPrice+sugarPrice)*number;
            totalamount.setText(String.valueOf(totalPay));
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getSelectedItem().equals("Dano")) {
            Toast.makeText(this, "Dano", Toast.LENGTH_SHORT).show();
            this.milkPrice = 4;
        }
        else if (adapterView.getSelectedItem().equals("Arong")) {
            Toast.makeText(this, "Arong", Toast.LENGTH_SHORT).show();
            this.milkPrice = 5;
        }
        else if (adapterView.getSelectedItem().equals("Diploma")) {
            Toast.makeText(this, "Diploma", Toast.LENGTH_SHORT).show();
            this.milkPrice = 6;
        }
        else if (adapterView.getSelectedItem().equals("white sugar")) {
            Toast.makeText(this, "white sugar", Toast.LENGTH_SHORT).show();
            this.sugarPrice = 5;
        }
        else if (adapterView.getSelectedItem().equals("brown sugar")) {
            Toast.makeText(this, "brown sugar", Toast.LENGTH_SHORT).show();
            this.sugarPrice = 4;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}