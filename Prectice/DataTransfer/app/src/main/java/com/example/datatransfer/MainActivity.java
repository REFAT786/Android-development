package com.example.datatransfer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.edit_text_id);
        Button button = findViewById(R.id.button_id);

        textView = findViewById(R.id.show_text_id);

        button.setOnClickListener(view -> {
            int num = Integer.parseInt(String.valueOf(editText.getText())) ;
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("key",num);
            //startActivity(intent);
            //startActivityForResult(intent,1);
            activityResultLauncher.launch(intent);
        });


    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            int res = result.getResultCode();


            if(res == 1){
                Intent intent = result.getData();
                if(intent != null){
                    String data = intent.getStringExtra("com.example.datatransfer");
                    textView.setText(data);
                }
                else {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }


        }
    });
}