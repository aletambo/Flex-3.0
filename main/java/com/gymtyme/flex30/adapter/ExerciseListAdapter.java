package com.gymtyme.flex30.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gymtyme.flex30.R;
import com.gymtyme.flex30.exercises.Exercise;
import com.gymtyme.flex30.views.ExerciseListItem;

import java.util.ArrayList;
import java.util.BitSet;


//The exercise list adapter is user to set up the list view with the selectable items, its composed of multiple setter and getter functions
//has functions to coordinate the checkboxes at the various view
public class ExerciseListAdapter extends BaseAdapter {
    private ArrayList<Exercise> exercises;
    private LayoutInflater inflator;
    private Context context;

    public ExerciseListAdapter(ArrayList<Exercise> exercises, Context context) {
        super();
        this.exercises = exercises;
        this.context = context;   //might not need this
        this.inflator = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    public ArrayList<Exercise> getItems() {
        return exercises;
    }

    public void setItems(ArrayList<Exercise> exercises){
        this.exercises = exercises;
    }


    @Override
    public Exercise getItem(int position) {
        return (null == exercises)?null : exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId_num() : position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ExerciseListItem tli;
        if(null == convertView){
            tli = (ExerciseListItem)View.inflate(context, R.layout.exercise_list_item, null);
        }
        else {
            tli = (ExerciseListItem) convertView;
        }
        tli.setExercise(exercises.get(i));
        return tli;
        }

    public void forceReload() {
        notifyDataSetChanged();
    }

    public void toggleTaskCompleteAtPosition(int i) {
        Exercise e = exercises.get(i);
        e.toggleComplete();
        notifyDataSetChanged();
    }

    public void removeChecked() {
        ArrayList<Exercise> checkedExercises = new ArrayList<Exercise>();
        for(Exercise e:exercises){
            if(e.isComplete()){
                checkedExercises.add(e);
            }
        }
        exercises.removeAll(checkedExercises);
        notifyDataSetChanged();
    }
}

