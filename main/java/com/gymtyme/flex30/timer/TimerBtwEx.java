package com.gymtyme.flex30.timer;


import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gymtyme.flex30.MainScreenActivity;
import com.gymtyme.flex30.R;
import com.gymtyme.flex30.WorkoutApplication;
import com.gymtyme.flex30.WorkoutListActivity;
import com.gymtyme.flex30.exercises.Exercise;

import static java.lang.Thread.sleep;

//this class directs the time between different exercises. it implements a timer and on finish it redirects to the timeractivity
public class TimerBtwEx extends Activity {

   //initializes all the required variables to make this class run
    TextView timer;
    TextView exerciseNext;
    TextView numberSets;
    TextView repNumbers;
    Button pause;
    CountDownTimer countDownTimer;

    //count down timer variables required
    private long timerBtwEx;
    private long millisRemaining;
    private int isPaused = 2;
    private WorkoutApplication app;
    private int workoutNum;
    private int exercise_counter = 0;

    //on creates links up all the views of the xml file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_btw_ex);
        app = (WorkoutApplication) getApplication();
        exercise_counter = app.getExerciseNum();
        timer = (TextView) findViewById(R.id.timer);
        exerciseNext = (TextView) findViewById(R.id.exerciseNext);
        numberSets = (TextView) findViewById(R.id.numberSets);
        repNumbers = (TextView) findViewById(R.id.repNumber);
        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer(view);
                switch (isPaused) {
                    case 1:
                        pauseTimer(view);
                        isPaused = 2;
                        break;
                    case 2:
                        pauseTimer(view);
                        isPaused = 1;
                        break;
                }
            };
        });

        //loads all the necessary information for the next exercise
        workoutNum = app.getRunningWorkout();
        timerBtwEx = Long.parseLong(app.getCurrentWorkout().get(workoutNum).getTimeBetweenExercises())*1000;
        Exercise e = app.getCurrentWorkout().get(workoutNum).getCurrentExercise().get(exercise_counter);
        exerciseNext.setText("Next Exercise: " + e.getName()); // add here the name of the next exercise
        numberSets.setText("Sets: " + e.getSets()); //add here the number of sets for next exercise
        repNumbers.setText("Reps per set: " + e.getReps()); //add here the number of reps per set


        //starts the countdowntimer as soon as the page is created
        countDownTimer = new CountDownTimer(timerBtwEx, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {
                millisRemaining = millisUntilFinished;
                timer.setText("" +  millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timer.setText("Workout!"); //try to add a sound here
                Intent intent = new Intent(TimerBtwEx.this, TimerActivity.class);
                startActivity(intent);
            }
        };

        countDownTimer.start();
    }

    //this function puases the timer and gives it the possibility to be restarted from the same button
    public void pauseTimer(View view) {

        if(isPaused == 1){
            countDownTimer.start();
        }
        else if(isPaused == 2){
            timerBtwEx = millisRemaining;
            countDownTimer.cancel();

            timer.setText("" + timerBtwEx / 1000);
        }
    }

    //cancel button on the screen
    public void cancelWorkout(View view) {
        timer.setText("Workout Cancelled");

        Intent back = new Intent(TimerBtwEx.this, MainScreenActivity.class); //this bring you back to the homepage
        startActivity(back);
    }

}