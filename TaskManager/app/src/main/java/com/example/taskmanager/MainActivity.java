package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText titletextid;
    private Button time,date,cancelbutton;
    private ImageButton back,submit;
    private Spinner spinner;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.timeid);
        date = findViewById(R.id.dateid);
        spinner = findViewById(R.id.spinnerid);
        back = findViewById(R.id.backbutton);
        cancelbutton = findViewById(R.id.cancelbuttonid);
        submit = findViewById(R.id.submit_button);
        titletextid = findViewById(R.id.titletextid);

        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.sp));
        spinner.setAdapter(spinnerCountShoesArrayAdapter);

        time.setOnClickListener(this);
        date.setOnClickListener(this);
        back.setOnClickListener(this);
        cancelbutton.setOnClickListener(this);
        submit.setOnClickListener(this);
        titletextid.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //auto genarete

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.dateid){
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (datePicker, year, month, day) -> date.setText(day + "/" + (month + 1) + "/" + year), year, month, dayOfMonth);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
        if(view.getId() == R.id.timeid){
            final Calendar c = Calendar.getInstance();

            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view1, hourOfDay, minute1) -> {

                        time.setText(hourOfDay + ":" + minute1);
                    }, hour, minute, false);

            timePickerDialog.show();
        }
        if (view.getId() == R.id.backbutton) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.cancelbuttonid){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.submit_button){
            String str = titletextid.getText().toString();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("key"," ");
            startActivity(intent);
        }


    }
}