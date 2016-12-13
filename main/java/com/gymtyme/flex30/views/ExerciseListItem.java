package com.gymtyme.flex30.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import com.gymtyme.flex30.exercises.Exercise;
import com.gymtyme.flex30.workouts.Workout;


//the exercise list item class will format the individual list configurations for the exercises.
    //this class will be used when the exercises are displayed as the user is adding them
public class ExerciseListItem extends LinearLayout {

    private CheckedTextView checkbox;
    private Exercise exercise;

    public ExerciseListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        checkbox = (CheckedTextView)findViewById(android.R.id.text1);
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
        checkbox.setText(exercise.getName());
        checkbox.setChecked(exercise.isComplete());
    }


}
