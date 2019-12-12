package com.example.myfragmentsapp;

import androidx.annotation.NonNull;

public class Player {
    protected String name;
    protected float score;

    @NonNull
    @Override
    public String toString() {
        return name + "," + score + ",";
    }

    public Player(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
