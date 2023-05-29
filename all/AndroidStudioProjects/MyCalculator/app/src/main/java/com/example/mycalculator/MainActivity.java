package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonone,buttontwo,buttonthree,buttonfour,buttonfive,buttonsix,buttonseven,buttoneight,buttonnine,buttonpluse,buttonminus,buttongun,buttonvag,buttonequalto,buttonzerozero,buttonzero,buttonreset;
    private TextView inputtext;
    private double number1=0,number2=0,result=0;
    private String operator = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonone = findViewById(R.id.button_one);
        buttontwo = findViewById(R.id.button_two);
        buttonthree = findViewById(R.id.button_three);
        buttonfour = findViewById(R.id.button_four);
        buttonfive = findViewById(R.id.button_five);
        buttonsix = findViewById(R.id.button_six);
        buttonseven = findViewById(R.id.button_seven);
        buttoneight = findViewById(R.id.button_eight);
        buttonnine = findViewById(R.id.button_nine);
        buttonpluse = findViewById(R.id.button_pluse);
        buttonminus = findViewById(R.id.button_minus);
        buttongun = findViewById(R.id.button_gun);
        buttonvag = findViewById(R.id.button_vag);
        buttonequalto = findViewById(R.id.button_equal);
        buttonzerozero = findViewById(R.id.button_zero_zero);
        buttonzero = findViewById(R.id.button_zero);
        inputtext = findViewById(R.id.inputtext);
        buttonreset = findViewById(R.id.buttonreset);

        buttonone.setOnClickListener(this);
        buttontwo.setOnClickListener(this);
        buttonthree.setOnClickListener(this);
        buttonfour.setOnClickListener(this);
        buttonfive.setOnClickListener(this);
        buttonsix.setOnClickListener(this);
        buttonseven.setOnClickListener(this);
        buttoneight.setOnClickListener(this);
        buttonnine.setOnClickListener(this);
        buttonzero.setOnClickListener(this);
        buttonzerozero.setOnClickListener(this);
        buttonpluse.setOnClickListener(this);
        buttonminus.setOnClickListener(this);
        buttongun.setOnClickListener(this);
        buttonvag.setOnClickListener(this);
        buttonequalto.setOnClickListener(this);
        buttonreset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.button_one){

            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=1;
                }else{
                    number1 = number1*10+1;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=1;
                }else{
                    number2 = number2*10+1;
                }
                inputtext.setText(String.valueOf(number2));
            }

        }
        if(view.getId()==R.id.button_two){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=2;
                }else{
                    number1 = number1*10+2;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=2;
                }else{
                    number2 = number2*10+2;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_three){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=3;
                }else{
                    number1 = number1*10+3;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=3;
                }else{
                    number2 = number2*10+3;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_four){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=4;
                }else{
                    number1 = number1*10+4;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=4;
                }else{
                    number2 = number2*10+4;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_five){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=5;
                }else{
                    number1 = number1*10+5;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=5;
                }else{
                    number2 = number2*10+5;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_six){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=6;
                }else{
                    number1 = number1*10+6;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=6;
                }else{
                    number2 = number2*10+6;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_seven){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=7;
                }else{
                    number1 = number1*10+7;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=7;
                }else{
                    number2 = number2*10+7;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_eight){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=8;
                }else{
                    number1 = number1*10+8;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=8;
                }else{
                    number2 = number2*10+8;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_nine){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=9;
                }else{
                    number1 = number1*10+9;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=9;
                }else{
                    number2 = number2*10+9;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_zero){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=0;
                }else{
                    number1 = number1 * 10;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=0;
                }else{
                    number2 = number2 * 10;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_zero_zero){
            if(Objects.equals(operator, "")){
                if(number1==0){
                    number1=0;
                }else{
                    number1 = number1 * 100;
                }
                inputtext.setText(String.valueOf(number1));
            }else{
                if(number2==0){
                    number2=0;
                }else{
                    number2 = number2 * 100;
                }
                inputtext.setText(String.valueOf(number2));
            }
        }
        if(view.getId()==R.id.button_pluse){
            inputtext.setText("+");
            operator = "+";
        }
        if(view.getId()==R.id.button_minus){
            inputtext.setText("-");
            operator = "-";
        }
        if(view.getId()==R.id.button_gun){
            inputtext.setText("*");
            operator = "*";
        }
        if(view.getId()==R.id.button_vag){
            inputtext.setText("/");
            operator = "/";
        }
        if(view.getId()==R.id.button_equal){
            if(Objects.equals(operator, "+")){
                result = number1 + number2;

            }
            else if(Objects.equals(operator, "-")){
                result = number1 - number2;
                number1 = 0;
                number2 = 0;
            }
            else if(Objects.equals(operator, "*")){
                result = number1 * number2;
                number1 = 0;
                number2 = 0;
            }
            else if(Objects.equals(operator, "/")){
                result = number1 / number2;
                number1 = 0;
                number2 = 0;
            }
            else {
                result= 0 ;
                number1 = 0;
                number2 = 0;
            }
            inputtext.setText(String.valueOf(result));

        }

        if(view.getId()==R.id.buttonreset){
            inputtext.setText("");
            operator = "";
            number1 = 0;
            number2 = 0;
            result = 0;

        }
    }
}