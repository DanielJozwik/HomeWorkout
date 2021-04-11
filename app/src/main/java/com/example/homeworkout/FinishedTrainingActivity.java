package com.example.homeworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinishedTrainingActivity extends AppCompatActivity {
    float caloriesBurned;
    TextView caloriesBurnedTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_training);
        caloriesBurnedTextView=findViewById(R.id.caloriesBurnedTextView);
        caloriesBurned=getIntent().getFloatExtra("caloriesBurned",0);
        caloriesBurnedTextView.setText(String.valueOf(caloriesBurned));
    }
    public void trainingFinish(View view){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
