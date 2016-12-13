package com.gymtyme.flex30;

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

//this activity is responsible to add and store workouts
//the workout are stored in a workout list, while they are saved in a .txt file
public class AddWorkoutActivity extends WorkoutManagerActivity {

    private EditText workoutNameEditText;
    private int counterWork;
    private Button addButton;
    private Button cancelButton;
    private EditText workoutTimeBetween;
    private WorkoutApplication app;
    private FileWriter fileWriter;
    private WorkoutListAdapter wla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_workout);
        setUpViews();
    }

    protected void cancel() {
        finish();
    }

    protected void addWorkout() {

        String workoutName = workoutNameEditText.getText().toString();
        String tbe = (workoutTimeBetween.getText().toString());
        app = (WorkoutApplication) getApplication();
        counterWork = app.getWorkoutNum();
        String FILENAME = "Workout_"+Integer.toString(counterWork)+".txt";
        FileOutputStream fOut;
        BufferedWriter bw;
        try {
            fOut = openFileOutput(FILENAME, MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fOut));
            bw.write(workoutName);
            bw.newLine();
            bw.write(tbe);
            bw.newLine();
            Workout w = new Workout(workoutName, tbe, counterWork);
            getWorkoutManagerApplication().addWorkout(w);
            bw.close();
            Toast.makeText(getBaseContext(), workoutName+" saved", Toast.LENGTH_SHORT).show();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        app.setExerciseNum(0);
        Intent intent = new Intent(AddWorkoutActivity.this, ViewExerciseActivity.class);
        startActivity(intent);
    }

//sets up the view for the linked xml file
    private void setUpViews() {
        workoutNameEditText = (EditText) findViewById(R.id.workout_name);
        workoutTimeBetween = (EditText) findViewById(R.id.time_between);
        addButton = (Button) findViewById(R.id.create_workout_button);
        cancelButton = (Button) findViewById(R.id.cancel_create_workout_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addWorkout();

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancel();
            }


        });
    }

}