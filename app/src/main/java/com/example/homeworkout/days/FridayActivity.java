package com.example.homeworkout.days;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.homeworkout.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class FridayActivity extends AppCompatActivity {


    List<String> training_typeList= new ArrayList<>();
    int chooseTraining ;
    ListView ExerciseList;
    String day;
    ListView exercises;
    Spinner spinner;
    ArrayAdapter<String> arrayAdapter ;
    ArrayAdapter<String> arrayAdapter2 ;
    ArrayAdapter<String> arrayAdapter3 ;
    ArrayAdapter<String> arrayAdapter4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daytraining);
        spinner = findViewById(R.id.spinner1);
        exercises = (ListView) findViewById(R.id.exerciseList);
        Button ok = (Button) findViewById(R.id.ok);
        training_typeList.add("Mieśnie Brzucha");
        training_typeList.add("Klatka Piersiowa");
        training_typeList.add("Nogi");
        training_typeList.add("Barki i Plecy");

        String[] Stomach = new String[] {
                "SIT-UPS",
                "SIDE BRIDGES LEFT",
                "SIDE BRIDGES RIGHT",
                "V-UP",
                "RUSSIAN TWIST",
                "BUTT BRIDGE",

        };
        String[] Chest= new String[] {
                "BURPEES",
                "PUSH-UP & ROTATION",
                "DIAMOND PUSH_UPS",
                "SPIDERMAN PUSH-UPS",
                "BOX PUSH-UPS",
                "DECLINE PUSH-UPS",
        };
        String[] Legs= new String[] {

                "SQUATS",
                "CURSTY LUNGES",
                "JUMPING SQUATS",
                "GLUTE KICK BACK LEFT",
                "GLUTE KICK BACK RIGHT",
                "PISTOLS",
        };
        String[] ShouldersAndBack= new String[] {
                "HYPEREXTENSION",
                "PIKE WALK",
                "FLOOR Y RAISES",
                "REVERSE SNOW ANGELS",
                "SUPINE PUSH UP",
                "REVERSE PUSH-UPS",
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, training_typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseTraining = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final List<String> stomach_list= new ArrayList<String>(Arrays.asList(Stomach));
        final List<String> chest_list= new ArrayList<String>(Arrays.asList(Chest));
        final List<String> legs_list= new ArrayList<String>(Arrays.asList(Legs));
        final List<String> shoulderandback_list= new ArrayList<String>(Arrays.asList(ShouldersAndBack));


        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, stomach_list);
        arrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, chest_list);
        arrayAdapter3 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, legs_list);
        arrayAdapter4 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, shoulderandback_list);



        ok.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(chooseTraining){
                    case 0:
                        exercises.setAdapter(arrayAdapter);
                        break;
                    case 1:
                        exercises.setAdapter(arrayAdapter2);
                        break;
                    case 2:
                        exercises.setAdapter(arrayAdapter3);
                        break;
                    case 3:
                        exercises.setAdapter(arrayAdapter4);
                        break;
                }

            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("chooseTraining" ,spinner.getSelectedItemPosition());
        setResult(RESULT_OK, resultIntent);
    }
    @Override
    protected void onResume() {
        super.onResume();


        chooseTraining= getIntent().getIntExtra("Training_day5", 0);

        spinner.setSelection(chooseTraining);


        switch(chooseTraining){
            case 0:
                exercises.setAdapter(arrayAdapter);
                break;
            case 1:
                exercises.setAdapter(arrayAdapter2);
                break;
            case 2:
                exercises.setAdapter(arrayAdapter3);
                break;
            case 3:
                exercises.setAdapter(arrayAdapter4);
                break;
        }

    }




    public void fromTrainingDayToMenu(View view) {



        Intent resultIntent = new Intent();
        resultIntent.putExtra("chooseTraining" ,spinner.getSelectedItemPosition());
        setResult(RESULT_OK, resultIntent);
        finish();
    }



}

