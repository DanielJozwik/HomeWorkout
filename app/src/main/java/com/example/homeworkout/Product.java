package com.example.homeworkout;

import androidx.annotation.NonNull;

public class Product  {
    private String productName;
    private int numberOfCalories;
    public Product(String productName, int numberOfCalories){
        this.numberOfCalories = numberOfCalories;
        this.productName = productName;
    }

    public void setNumberOfCalories(int numberOfCalories) {
        this.numberOfCalories = numberOfCalories;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumberOfCalories() {
        return numberOfCalories;
    }
    public String getProductName() {
        return productName;
    }

    @NonNull
    @Override
    public String toString() {
        String nameAndCalories =productName+"  ["+String.valueOf(numberOfCalories)+"kcal]";
        return nameAndCalories;
    }
}
