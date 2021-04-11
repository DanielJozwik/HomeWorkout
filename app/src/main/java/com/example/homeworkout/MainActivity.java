package com.example.homeworkout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String trainingType;
    float calories;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    float caloriesBurned;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }
    public void stomachMusclesTraining(View view) {
        trainingType="stomachMusclesTraining";
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("trainingType",trainingType);
        startActivityForResult(intent, 1);
    }
    public void chestTraining(View view) {
        trainingType="chestTraining";
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("trainingType",trainingType);
        startActivityForResult(intent, 1);
    }
    public void legsTraining(View view) {
        trainingType="legsTraining";
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("trainingType",trainingType);
        startActivityForResult(intent, 1);
    }
    public void shouldersAndBackTraining(View view) {
        trainingType="shouldersAndBackTraining";
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("trainingType",trainingType);
        startActivityForResult(intent, 1);
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 4){
            if (resultCode == RESULT_OK) {
                calories = data.getFloatExtra("calories", 0);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem menuitem){
            switch(menuitem.getItemId()) {
                case R.id.nav_home:
                    break;
                case R.id.nav_diet:
                    Intent intent2 =new Intent(MainActivity.this,MealsActivity.class);
                    intent2.putExtra("calories",calories);
                    startActivityForResult(intent2,3);
                    break;
                case R.id.nav_settings:
                    Intent intent3 =new Intent(MainActivity.this,SettingsActivity.class);
                    startActivityForResult(intent3,4);
                    break;
            }
        return true;
        }
    }


