package com.example.watchapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


public class StopWatchFragment extends Fragment {
    private TextView mTextView;
    private Button mStartButton, mPauseButton, mResetButton, mResumeButton ;
    private Handler mHandler;
    private long mStartTime, mTimeInMillis;
    private boolean mIsRunning;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mTimeInMillis = System.currentTimeMillis() - mStartTime;
            int minutes = (int) (mTimeInMillis / 1000) / 60;
            int seconds = (int) (mTimeInMillis / 1000) % 60;
            int milliseconds = (int) (mTimeInMillis % 1000);
            String time = String.format(Locale.getDefault(), "%02d:%02d.%03d", minutes, seconds, milliseconds);
            mTextView.setText(time);
            mHandler.postDelayed(this, 10);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stop_watch, container, false);

        mTextView = view.findViewById(R.id.textView);
        mStartButton = view.findViewById(R.id.startButton_id);
        mPauseButton = view.findViewById(R.id.stopButton_id);
        mResetButton = view.findViewById(R.id.resetButton_id);
        mResumeButton = view.findViewById(R.id.resumeButton_id);

        mHandler = new Handler();

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsRunning) {

                    mHandler.postDelayed(mRunnable, 10);
                    mIsRunning = true;
                }

            }
        });

        mResumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mIsRunning) {

                    mHandler.postDelayed(mRunnable, 10);
                    mIsRunning = true;
                }

            }
        });

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsRunning) {
                    mHandler.removeCallbacks(mRunnable);
                    mIsRunning = false;
                }
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeCallbacks(mRunnable);
                mTextView.setText("00:00:000");
                mTimeInMillis = 0;
                mIsRunning = false;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mIsRunning) {
            mHandler.postDelayed(mRunnable, 10);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }
}
