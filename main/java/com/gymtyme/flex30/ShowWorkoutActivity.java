package com.gymtyme.flex30;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.gymtyme.flex30.adapter.ExerciseListAdapter;
import com.gymtyme.flex30.adapter.WorkoutListAdapter;

import java.io.IOException;


public class ShowWorkoutActivity extends ListActivity {
    private ExerciseListAdapter adapterE;
    private WorkoutListAdapter adapterW;
    private ListView listView;
    private WorkoutApplication app;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_workout);
        setUpViews();
        app = (WorkoutApplication) getApplication();
        int i = app.getWorkoutNum() - 1;
        try {
            adapterE = new ExerciseListAdapter(app.getCurrentExercise(i), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView.setAdapter(adapterE);


    }


    private void setUpViews() {
    }
}
