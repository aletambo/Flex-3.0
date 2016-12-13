package com.gymtyme.flex30.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import com.gymtyme.flex30.workouts.Workout;

//the workout list item class formats the set up of the list view for the workouts.
//this class is used to display the list view the workouts as a list
public class WorkoutListItem extends LinearLayout {

    private CheckedTextView checkbox;
    private Workout workout;

    public WorkoutListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        checkbox = (CheckedTextView)findViewById(android.R.id.text1);
    }

    public Workout getWorkout() {
        return workout;
    }


    public void setWorkout(Workout workout) {
        this.workout = workout;
        String workSomething = workout.getName() + "   " + workout.getTimeBetweenExercises();
        checkbox.setText(workSomething);
        checkbox.setChecked(workout.isComplete());
    }
}
