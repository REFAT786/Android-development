package com.ibss.myreview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private ReviewManager reviewManager;
    private ReviewInfo reviewInfo;
    private Button reviewButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reviewButton = findViewById(R.id.reviewBTN_id);

        reviewManager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = reviewManager.requestReviewFlow();

        request.addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                reviewInfo = task.getResult();
            }
            else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        reviewButton.setOnClickListener(v -> {
            Task<Void> flow = reviewManager.launchReviewFlow(MainActivity.this, reviewInfo);
            flow.addOnCompleteListener(task1 -> Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_SHORT).show());
        });

    }
}