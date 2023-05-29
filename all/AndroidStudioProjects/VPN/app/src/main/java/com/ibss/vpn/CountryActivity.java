package com.ibss.vpn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CountryActivity extends AppCompatActivity {

    TextView cancelBtnText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        cancelBtnText = findViewById(R.id.cancelBtnText_id);
        cancelBtnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CountryActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}