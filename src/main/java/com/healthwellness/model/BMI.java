package com.healthwellness.model;

public class BMI {
    private int userId;
    private float weight;
    private float height;
    private float bmiValue;
    private String bmiCategory;

    public BMI(int userId, float weight, float height, float bmiValue, String bmiCategory) {
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.bmiValue = bmiValue;
        this.bmiCategory = bmiCategory;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getBmiValue() {
        return bmiValue;
    }

    public void setBmiValue(float bmiValue) {
        this.bmiValue = bmiValue;
    }

    public String getBmiCategory() {
        return bmiCategory;
    }

    public void setBmiCategory(String bmiCategory) {
        this.bmiCategory = bmiCategory;
    }
}