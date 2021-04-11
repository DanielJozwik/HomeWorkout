package com.example.homeworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RestActivity extends AppCompatActivity {
    ProgressBar timeRestProgressBar;
    TextView timeRestTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        timeRestTextView=findViewById(R.id.timeRestTextView);
        timeRestProgressBar=findViewById(R.id.timeRestProgressBar);
        CountDownTimer countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTime(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                restFinish();
            }
        };
            countDownTimer.start();
    }
    private void updateTime(long millisUntilFinished) {
        int time = (int) (millisUntilFinished / 1000);
        timeRestTextView.setText(String.format("%d ", time));
        timeRestProgressBar.setProgress(time);
    }
    public void restFinish(){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    public void restFinish(View view){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
