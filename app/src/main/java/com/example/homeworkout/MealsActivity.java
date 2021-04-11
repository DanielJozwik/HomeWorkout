package com.example.homeworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MealsActivity extends AppCompatActivity {
    ListView productsList;
    EditText productNameEditText;
    EditText numberOfCaloriesEditText;
    List<Product> products = new ArrayList<>();
    TextView caloriesConsumed;
    TextView caloriesToEatenTextView;
    int allCalories;
    float caloriesToEaten;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        productNameEditText = findViewById(R.id.productName);
        numberOfCaloriesEditText = findViewById(R.id.numberOfCalories);
        productsList = findViewById(R.id.productsList);
        caloriesConsumed = findViewById(R.id.caloriesConsumed);
        caloriesConsumed.setText(String.valueOf(allCalories));
        caloriesToEatenTextView = findViewById(R.id.caloriesToBeEaten);
        caloriesToEaten = getIntent().getFloatExtra("calories",0);
        caloriesToEatenTextView.setText(String.valueOf(caloriesToEaten));
        caloriesConsumed.setText(String.valueOf(allCalories));
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences("shared",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("allCalories", Integer.parseInt(caloriesConsumed.getText().toString()));
        editor.putFloat("caloriesToEaten",Float.parseFloat(caloriesToEatenTextView.getText().toString()));
        editor.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("shared",MODE_PRIVATE);
        allCalories = sharedPref.getInt("allCalories", 0);
        caloriesToEaten = sharedPref.getFloat("caloriesToEaten", 0);
        if(getIntent().getFloatExtra("calories",0)!=0) {
            caloriesToEaten = getIntent().getFloatExtra("calories", 0);
        }
        caloriesToEatenTextView.setText(String.valueOf(caloriesToEaten));
        caloriesConsumed.setText(String.valueOf(allCalories));
    }
    public void fromMealsToMenu(View view) {
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    public void addProducts(View view){
        try{
            String name = productNameEditText.getText().toString();
            int calories = Integer.parseInt(numberOfCaloriesEditText.getText().toString());
            products.add(new Product(name,calories));
            allCalories = allCalories + calories;
            String caloriesText = String.valueOf(allCalories);
            products.get(i).toString();
            initList();
            setCalories(caloriesText);
            i++;
        }catch (Exception ex){
            Context context = getApplicationContext();
            CharSequence text = "Dane produktu wprowadzone niepoprawnie";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    public void reset(View view) {
        allCalories=0;
        caloriesToEaten=0;
        caloriesToEatenTextView.setText(String.valueOf(caloriesToEaten));
        caloriesConsumed.setText(String.valueOf(allCalories));
    }

    public void initList(){
        ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(this,R.layout.list, products);
        productsList.setAdapter(adapter);
    }
    public void setCalories(String calories){
        caloriesConsumed.setText(calories);
    }
}
