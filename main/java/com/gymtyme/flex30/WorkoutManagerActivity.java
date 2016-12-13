package com.gymtyme.flex30;

import android.app.Activity;

//this return the app so that we can call any workout list at any time
public class WorkoutManagerActivity extends Activity {

    protected WorkoutApplication getWorkoutManagerApplication() {
        WorkoutApplication wa = (WorkoutApplication)getApplication();
        return wa;
    }
}
