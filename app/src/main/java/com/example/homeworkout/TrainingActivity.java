package com.example.homeworkout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import java.util.ArrayList;
import java.util.List;

public class TrainingActivity extends AppCompatActivity  {

    private List<Exercise> exerciseList;
    private Exercise exercise;
    private int currentExercise = 0;
    float caloriesBurned=0;
    TextView numberOfRepetitionsTextView;
    TextView nameExerciseTextView;
    int numberOfRepetitions=15;
    String trainingType;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        trainingType=getIntent().getStringExtra("trainingType");
        numberOfRepetitionsTextView=findViewById(R.id.numberOfRepetitionsTextView);
        nameExerciseTextView=findViewById(R.id.exerciseNameTextView);
        numberOfRepetitionsTextView.setText(String.valueOf(numberOfRepetitions));
        videoView=findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        switch(trainingType) {
            case "stomachMusclesTraining":
                exerciseList = new ArrayList<>();
                exerciseList.add(new Exercise("SIT-UPS", 1.3f,"android.resource://"+getPackageName()+"/"+R.raw.situps));
                exerciseList.add(new Exercise("SIDE BRIDGES LEFT",2,"android.resource://"+getPackageName()+"/"+R.raw.leftsidebridge));
                exerciseList.add(new Exercise("SIDE BRIDGES RIGHT",2,"android.resource://"+getPackageName()+"/"+R.raw.side_bridge));
                exerciseList.add(new Exercise("V-UP",2.3f,"android.resource://"+getPackageName()+"/"+R.raw.upv_vup));
                exerciseList.add(new Exercise("RUSSIAN TWIST",1.1f,"android.resource://"+getPackageName()+"/"+R.raw.russian));
                exerciseList.add(new Exercise("BUTT BRIDGE",1.5f,"android.resource://"+getPackageName()+"/"+R.raw.buttbridge));
                break;
            case "chestTraining":
                exerciseList = new ArrayList<>();
                exerciseList.add(new Exercise("BURPEES",3.5f,"android.resource://"+getPackageName()+"/"+R.raw.burpee));
                exerciseList.add(new Exercise("PUSH-UP & ROTATION",2.5f,"android.resource://"+getPackageName()+"/"+R.raw.pushupwithrotation));
                exerciseList.add(new Exercise("DIAMOND PUSH_UPS",3,"android.resource://"+getPackageName()+"/"+R.raw.diamondpush));
                exerciseList.add(new Exercise("SPIDERMAN PUSH-UPS",2.9f,"android.resource://"+getPackageName()+"/"+R.raw.pushup_spiderman));
                exerciseList.add(new Exercise("BOX PUSH-UPS",3,"android.resource://"+getPackageName()+"/"+R.raw.pushupbox));
                exerciseList.add(new Exercise("DECLINE PUSH-UPS",3.3f,"android.resource://"+getPackageName()+"/"+R.raw.declinepushup));
                break;
            case "legsTraining":
                exerciseList = new ArrayList<>();
                exerciseList.add(new Exercise("SQUATS",2.4f,"android.resource://"+getPackageName()+"/"+R.raw.squats));
                exerciseList.add(new Exercise("CURSTY LUNGES",2,"android.resource://"+getPackageName()+"/"+R.raw.crusty_lunges));
                exerciseList.add(new Exercise("JUMPING SQUATS",3.5f,"android.resource://"+getPackageName()+"/"+R.raw.jumpsquats));
                exerciseList.add(new Exercise("GLUTE KICK BACK LEFT",2.5f,"android.resource://"+getPackageName()+"/"+R.raw.leftglutebackkick));
                exerciseList.add(new Exercise("GLUTE KICK BACK RIGHT",2.5f,"android.resource://"+getPackageName()+"/"+R.raw.right_glutebackkick));
                exerciseList.add(new Exercise("PISTOLS",2.8f,"android.resource://"+getPackageName()+"/"+R.raw.pistols));
                break;
            case "shouldersAndBackTraining":
                exerciseList = new ArrayList<>();
                exerciseList.add(new Exercise("HYPEREXTENSION",2.5f,"android.resource://"+getPackageName()+"/"+R.raw.hyperextension));
                exerciseList.add(new Exercise("PIKE WALK",2.7f,"android.resource://"+getPackageName()+"/"+R.raw.pikewalk));
                exerciseList.add(new Exercise("FLOOR Y RAISES",2.8f,"android.resource://"+getPackageName()+"/"+R.raw.flooryraises));
                exerciseList.add(new Exercise("REVERSE SNOW ANGELS",2.9f,"android.resource://"+getPackageName()+"/"+R.raw.snowangels));
                exerciseList.add(new Exercise("SUPINE PUSH UP",2.5f,"android.resource://"+getPackageName()+"/"+R.raw.spinepushup));
                exerciseList.add(new Exercise("REVERSE PUSH-UPS",2.7f,"android.resource://"+getPackageName()+"/"+R.raw.rewersepushup));
                break;
        }
        initActivity();
        currentExercise++;
    }
    public void nextExercise(View view) {
        exercise=exerciseList.get(currentExercise-1);
        caloriesBurned+=exercise.calculateBurnedCalories(Float.parseFloat(numberOfRepetitionsTextView.getText().toString()));
        if (currentExercise == exerciseList.size()) {
            Intent intent = new Intent(this, FinishedTrainingActivity.class);
            intent.putExtra("caloriesBurned",caloriesBurned);
            startActivityForResult(intent, 3);
        }
        else {
            Intent intent = new Intent(this, RestActivity.class);
            startActivityForResult(intent, 2);
        }
    }
    public void addRepetitions(View view){
        numberOfRepetitions++;
        numberOfRepetitionsTextView.setText(String.valueOf(numberOfRepetitions));
    }
    public void substractRepetitions(View view){
        numberOfRepetitions--;
        numberOfRepetitionsTextView.setText(String.valueOf(numberOfRepetitions));
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                initActivity();
                currentExercise++;
            }
        }
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void initActivity()
    {
        exercise=exerciseList.get(currentExercise);
        nameExerciseTextView.setText(exercise.getExerciseName());
        numberOfRepetitions = 15;
        numberOfRepetitionsTextView.setText(String.valueOf(numberOfRepetitions));
        Uri uri = Uri.parse(exercise.getVideoPath());
        videoView.setVideoURI(uri);
    }
}
