package com.gymtyme.flex30.workouts;

import android.widget.Toast;

import com.gymtyme.flex30.WorkoutApplication;
import com.gymtyme.flex30.WorkoutManagerActivity;
import com.gymtyme.flex30.exercises.Exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gymtyme.flex30.adapter.WorkoutListAdapter;
import com.gymtyme.flex30.workouts.Workout;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.*;

//the workout class stores all the information necessary for a workout
//it also has all the setters and getter required throughout the application.
public class Workout extends WorkoutManagerActivity {
    private String name;
    private int work_id;
    private boolean complete;
    private String timeBetweenExercises;
    private String exercise_num;
    private ArrayList<Exercise> currentExercise;
    private WorkoutApplication app;


    public Workout(String workName, String tbe, int id){
        this.name = workName;
        this.timeBetweenExercises = tbe;
        this.work_id=id;
        this.currentExercise = new ArrayList<Exercise>();
    }

    public Workout(){
    };


    public int getWorkoutId(){
        return work_id;
    }

    public void setWorkoutId(int id){
        this.work_id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTimeBetweenExercises(String tbe){
        this.timeBetweenExercises = tbe;
    }

    public String getTimeBetweenExercises() {
        return timeBetweenExercises;
    }

    public void setCurrentExercise(ArrayList<Exercise> currentExercise) {
        this.currentExercise = currentExercise;
    }

    public ArrayList<Exercise> getCurrentExercise() {
        return currentExercise;
    }


    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }

    public void toggleComplete() {
        complete = !complete;
    }
}
