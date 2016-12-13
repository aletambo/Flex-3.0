package com.gymtyme.flex30.timer;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gymtyme.flex30.MainScreenActivity;
import com.gymtyme.flex30.R;
import com.gymtyme.flex30.WorkoutApplication;
import com.gymtyme.flex30.WorkoutDoneActivity;
import com.gymtyme.flex30.WorkoutListActivity;
import com.gymtyme.flex30.exercises.Exercise;

import java.io.IOException;

//this class is responsible for the timer between sets. this coordinates the sets with a timer and all the buttons on the screen
public class TimerActivity extends Activity {

    //variables to initiate in this page
    TextView time;
    TextView setNumber;
    TextView exerciseName;
    TextView repNumber;
    TextView testTimer;
    CountDownTimer countDownTimer;
    WorkoutApplication app;

    //the variables required for the timer
    long timer;
    long millisRemaining;
    int counter = 1; ///this will be a counter to record the amount of sets done
    private int exercise_counter = 0;
    private int workout_counter =0;

    //on create it sets up all the elements in the xml file
    //these display all the information relevant to the exercise that is being displayed on the screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        time = (TextView) findViewById(R.id.time);
        setNumber = (TextView) findViewById(R.id.numberSets);
        exerciseName = (TextView) findViewById(R.id.exercise_name_timer);
        repNumber = (TextView) findViewById(R.id.repNumber);
        app = (WorkoutApplication)getApplication();

        exercise_counter = app.getExerciseNum();
        workout_counter = app.getRunningWorkout();

        setNumber.setText("Set Number: " + counter);
        Exercise e = app.getCurrentWorkout().get(workout_counter).getCurrentExercise().get(exercise_counter);

            repNumber.setText("Number of Reps: " + e.getReps());
        try {
            exerciseName.setText("Exercise: " + app.getCurrentExercise(workout_counter).get(exercise_counter).getName());
        } catch (IOException z) {
            z.printStackTrace();
        }
        String longToString = app.getCurrentWorkout().get(workout_counter).getCurrentExercise().get(exercise_counter).getTimeBetweenSets().trim();
        if(!longToString.equals(""))
        {
            timer = 1000*(Long.parseLong(longToString));
        }
        time.setText("" + timer / 1000 );
    }


    //starts the timer, this function has to be connected to the start button
    // when it gets clicked the timer starts, the timer display updates every second
    public void startTime(View view) {

        countDownTimer = new CountDownTimer(timer ,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisRemaining = millisUntilFinished;
                time.setText("" +  millisUntilFinished / 1000);
            }

            //set a counter that counts the amount of sets done. When the counter matches the amount of sets desired it will display the new screen
            //if this is the last exercise of the workout the else if statement will redirect to a workout done page
            @Override
            public void onFinish() { // what it does when the timer runs out, should bring to new set/exercise
                counter += 1;
                exercise_counter = app.getExerciseNum();
                int sets = Integer.parseInt(app.getCurrentWorkout().get(workout_counter).getCurrentExercise().get(exercise_counter).getSets());
                if(counter <= sets){
                    time.setText("Start!");
                    setNumber.setText("Set Number: " + counter);
                }
                else if (counter > sets && exercise_counter < app.getCurrentWorkout().get(workout_counter).getCurrentExercise().size() -1) {
                    time.setText("Exercise Done!");
                    setNumber.setText("");
                    exerciseName.setText("");
                    repNumber.setText("");
                    app.addExerciseNum();
                    Intent next = new Intent(TimerActivity.this, TimerBtwEx.class); //this should redirect to the next exercise on the list
                    startActivity(next);
                }
                else if (counter > sets && exercise_counter >= app.getCurrentWorkout().get(workout_counter).getCurrentExercise().size()-1){
                   // time.setText("Workout Done!");
                    setNumber.setText("");
                    exerciseName.setText("");
                    repNumber.setText("");
                    Intent next = new Intent(TimerActivity.this, WorkoutDoneActivity.class); //this should redirect to the next exercise on the list
                    startActivity(next);
                }
            }
        };

        countDownTimer.start();
    }



    //pause button, stops the timer and saves the remaining time so that when the timer restarts it is at the current point
    public void pauseTimer(View view) {
        timer = millisRemaining;
        countDownTimer.cancel();
    }

    //cancels the timer, goes back to homepage
    public void gotoBackPage(View view){
        if(countDownTimer == null){
            Intent back = new Intent(TimerActivity.this, MainScreenActivity.class); //page you want to be redirected to
            startActivity(back);
        }
        //countDownTimer.cancel();
        time.setText("Cancelled");

        Intent back = new Intent(TimerActivity.this, MainScreenActivity.class); //page you want to be redirected to
        startActivity(back);
    }
}
