package com.example.homeworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements Serializable {


    EditText age;
    EditText weight;
    EditText growth;
    EditText weightLoss;
    float calories;
    List<String> genderList = new ArrayList<>();
    int chooseTraining=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        growth = findViewById(R.id.growth);
        weightLoss = findViewById(R.id.weightLoss);
        genderList.add("Mężczyzna");
        genderList.add("Kobieta");
        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, genderList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseTraining = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void calculateCalories(View view) {
        try{
            int newAge = Integer.parseInt(age.getText().toString());
            float newWeight = Float.parseFloat(weight.getText().toString());
            int newGrowth = Integer.parseInt(growth.getText().toString());
            float newWeightLoss = Float.parseFloat(weightLoss.getText().toString());
            calories = 2000 - newAge*2 + newWeight + newGrowth - newWeightLoss*300;
        }catch (Exception ex){
            Context context = getApplicationContext();
            CharSequence text = "Dane zostały wpisane niepoprawnie";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            ex.printStackTrace();
        }
    }
    public void fromSettingsToMenu(View view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("calories",calories);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
