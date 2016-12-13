package com.gymtyme.flex30;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.gymtyme.flex30.adapter.WorkoutListAdapter;
import com.gymtyme.flex30.timer.TimerActivity;
import com.gymtyme.flex30.workouts.Workout;

import java.util.ArrayList;

//this activity is responsible for selecting the correct workout to run on the select workout screen
public class WorkoutListActivity extends ListActivity{

    private Button backButton;
    private ListView listView;
    private WorkoutApplication app;
    private WorkoutListAdapter adapter;
    private Button removeButton;
    private Button startButton;
    private int exercise_counter = 0;
    private int workout_num;
    private ArrayList<Workout> workouts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_list);
        app = (WorkoutApplication) getApplication();
        //workouts = app.loadWorkouts();
        workouts = app.getCurrentWorkout();
        setUpViews();
        adapter = new WorkoutListAdapter(workouts, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.forceReload();
        app = (WorkoutApplication) getApplication();
        workout_num = app.getWorkoutNum();

    }

    protected void removeChecked() {
        adapter.removeChecked();
    }

    private void setUpViews() {
        backButton = (Button)findViewById(R.id.back_button);
        listView = (ListView) findViewById(android.R.id.list);
        removeButton = (Button)findViewById(R.id.work_remove_button);
        startButton = (Button)findViewById(R.id.start_workout_button);
        removeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                removeChecked();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutListActivity.this, MainScreenActivity.class);
                startActivity(intent);
            }
        });
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int rwn = 0;
                for(int i = 0; i < workouts.size(); i++){
                    if(workouts.get(i).isComplete()){
                        rwn = workouts.get(i).getWorkoutId();
                    }
                }
                app.setExerciseNum(0);
                app.setRunningWorkout(rwn);
                Intent intent = new Intent(WorkoutListActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onListItemClick(ListView l, View v, int pos, long id){
        super.onListItemClick(l, v, pos, id);
        adapter.toggleTaskCompleteAtPosition(pos);
    }


}
