package com.gymtyme.flex30.exercises;

import com.gymtyme.flex30.workouts.Workout;


//this is the exercise class. it contains all the variables that are necessary for an exercise
//contains multiple setter and getter functions to store and retrieve data multiple times throughout the application
//the constructor initializes all the variables to the values passed to it
public class Exercise{
    private String name;
    private String sets;
    private String reps;
    private int id_num;
    private String timeBetweenSets;
    private Workout workout;
    private boolean complete;

    public Exercise(String exName, String exSets, String exReps, String timeBetweenSets, int id) {
        this.name = exName;
        this.sets = exSets;
        this.reps = exReps;
        this.id_num = id;
        this.timeBetweenSets = timeBetweenSets;
    }

    public Exercise(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId_num(int id){
        this.id_num = id;
    }

    public int getId_num(){
        return id_num;
    }

    public void setSets(String sets){
        this.sets = sets;
    }

    public String getSets(){
        return sets;
    }

    public void setReps(String reps){
        this.reps = reps;
    }

    public String getReps(){
        return reps;
    }

    public void setTimeBetweenSets(String tbs) {
        this.timeBetweenSets = tbs;
    }

    public String getTimeBetweenSets(){
        return timeBetweenSets;
    }

    public void setWorkout(Workout w){
        this.workout = w;
    }

    public Workout getWorkout(){
        return workout;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }

    public void toggleComplete() {
        complete = !complete;
    }
}