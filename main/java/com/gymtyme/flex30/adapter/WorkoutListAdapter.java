package com.gymtyme.flex30.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gymtyme.flex30.R;
import com.gymtyme.flex30.views.ExerciseListItem;
import com.gymtyme.flex30.views.WorkoutListItem;
import com.gymtyme.flex30.workouts.Workout;

import java.util.ArrayList;


//workout list adapter class is responsible for the list view check boxes.
public class WorkoutListAdapter extends BaseAdapter {
    private ArrayList<Workout> workouts;
    private Context context;
    private LayoutInflater inflator;

    public WorkoutListAdapter(ArrayList<Workout> workouts, Context context) {
        super();
        this.workouts = workouts;
        this.context = context; //might not need this
        this.inflator = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0;
    }

    @Override
    public Workout getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getWorkoutId() : position;
    }

    public ArrayList<Workout> getItems(){
        return workouts;
    }

    public void setItems(ArrayList<Workout> workout){
        this.workouts=workouts;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        WorkoutListItem tli;
        if(null == convertView){
            tli = (WorkoutListItem)View.inflate(context, R.layout.workout_list_item, null);
        }
        else {
            tli = (WorkoutListItem) convertView;
        }
        tli.setWorkout(workouts.get(i));
        return tli;
    }

    public void forceReload() {
        notifyDataSetChanged();
    }

    public void toggleTaskCompleteAtPosition(int pos) {
        Workout w = workouts.get(pos);
        w.toggleComplete();
        notifyDataSetChanged();
    }

    public void removeChecked() {
        ArrayList<Workout> checkedWorkouts = new ArrayList<Workout>();
        for(Workout w:workouts){
            if(w.isComplete()){
                checkedWorkouts.add(w);
            }
        }
        workouts.removeAll(checkedWorkouts);
        notifyDataSetChanged();
    }
    }