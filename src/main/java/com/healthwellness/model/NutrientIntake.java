package com.healthwellness.model;

import java.sql.Date;

public class NutrientIntake {
    private int id;
    private int userId;
    private String foodItem;
    private int calories;
    private float protein;
    private float carbs;
    private float fat;
    private Date intakeDate;

    // Constructors
    public NutrientIntake() {}

    public NutrientIntake(int userId, String foodItem, int calories, float protein, float carbs, float fat, Date intakeDate) {
        this.userId = userId;
        this.foodItem = foodItem;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.intakeDate = intakeDate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public Date getIntakeDate() {
        return intakeDate;
    }

    public void setIntakeDate(Date intakeDate) {
        this.intakeDate = intakeDate;
    }
}