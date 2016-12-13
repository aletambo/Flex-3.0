package com.gymtyme.flex30;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import static java.lang.Thread.sleep;

//this activity is responsible to show a page after the workout has been completed
public class WorkoutDoneActivity extends Activity{

    TextView WorkoutDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_done);

        WorkoutDone = (TextView) findViewById(R.id.WorkoutDone);
        WorkoutDone.setText("Workout Done!");

        gotoNextPage();
    }

    public void gotoNextPage() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main = new Intent(WorkoutDoneActivity.this, MainScreenActivity.class);
                startActivity(main);
            }
        },3000);
    }
}
