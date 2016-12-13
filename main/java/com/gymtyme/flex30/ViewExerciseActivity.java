package com.gymtyme.flex30;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.gymtyme.flex30.adapter.ExerciseListAdapter;
import com.gymtyme.flex30.exercises.Exercise;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

//this activity sets up the view for the exercise addition page
// we store and save the data retrieved by the user
//data stored in classes and saved in .txt files
public class ViewExerciseActivity extends ListActivity {

    //initializes all teh variables needed for the class
    private String exName;
    private String exReps;
    private String exSets;
    private String exTime;
    private int id;
    private Button addButton;
    private Button doneButton;
    private WorkoutApplication app;
    private ExerciseListAdapter adapter;
    private ListView listView;
    private int counter = 0;
    private Button removeButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_workout);
        setUpViews();
        app = (WorkoutApplication) getApplication();
        int i = app.getWorkoutNum()-1;
        adapter = new ExerciseListAdapter(app.getCurrentWorkout().get(i).getCurrentExercise(), this);
        listView.setAdapter(adapter);
    }


    @Override
    public void onResume(){
        super.onResume();
        adapter.forceReload();
        int i = app.getWorkoutNum()-1;
        try {
            adapter = new ExerciseListAdapter(app.getCurrentExercise(i), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView.setAdapter(adapter);
    }

    protected void onListItemClick(ListView l, View v, int pos, long id){
        super.onListItemClick(l, v, pos, id);
        adapter.toggleTaskCompleteAtPosition(pos);
    }

    protected void removeChecked() {
        adapter.removeChecked();

    }

    //sets up the view for this activity
    //it also calls a pop up view when the user clicks the add button to add an exercise
    // a list view is implemented to show the added exercises
    private void setUpViews() {
        final Context context = this;
        addButton = (Button)findViewById(R.id.add_Button);
        doneButton = (Button)findViewById(R.id.complete_create);
        removeButton = (Button)findViewById(R.id.edit_button);
       //
        listView = (ListView) findViewById(android.R.id.list);
        removeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                removeChecked();
            }


        });
        //the add button saves and store the exercise classes
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("New Exercise");
                final EditText input = new EditText(context);
                final EditText sets = new EditText(context);
                final EditText reps = new EditText(context);
                final EditText time = new EditText(context);
                input.setHint("Exercise Name");
                sets.setHint("Sets");
                reps.setHint("Reps");
                time.setHint("Rest Time");
                LinearLayout dialog= new LinearLayout(context);
                dialog.setOrientation(LinearLayout.VERTICAL);
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                sets.setInputType(InputType.TYPE_CLASS_NUMBER);
                reps.setInputType(InputType.TYPE_CLASS_NUMBER);
                time.setInputType(InputType.TYPE_CLASS_NUMBER);
                dialog.addView(input);
                dialog.addView(sets);
                dialog.addView(reps);
                dialog.addView(time);

                builder.setView(dialog);

                builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exName = input.getText().toString();
                        exSets = (sets.getText().toString());
                        exReps = (reps.getText().toString());
                        exTime = (time.getText().toString());
                        app.addExerciseNum();
                        int counter = app.getExerciseNum();
                        FileOutputStream fOut;
                        BufferedWriter bw;
                        int workoutNum = app.getWorkoutNum() - 1;
                        String FILENAME = "Workout_"+Integer.toString(workoutNum)+".txt";
                        try {
                            fOut = openFileOutput(FILENAME, MODE_APPEND);
                            bw = new BufferedWriter(new OutputStreamWriter(fOut));
                            bw.write(exName);
                            bw.newLine();
                            bw.write(exSets);
                            bw.newLine();
                            bw.write(exReps);
                            bw.newLine();
                            bw.write(exTime);
                            bw.newLine();
                            bw.close();
                            Toast.makeText(getBaseContext(), exName+" exercise saved", Toast.LENGTH_SHORT).show();
                            Exercise e = new Exercise(exName, exSets, exReps, exTime, counter);
                            app.getCurrentWorkout().get(workoutNum).getCurrentExercise().add(e);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
            });
        doneButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ViewExerciseActivity.this, MainScreenActivity.class);
                startActivity(intent);
            }
        });
        }
    }
