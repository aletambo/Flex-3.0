package com.gymtyme.flex30;

        import android.app.Application;
import android.widget.Toast;

import com.gymtyme.flex30.exercises.Exercise;
import com.gymtyme.flex30.workouts.Workout;


import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.FileReader;
import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.lang.reflect.Array;
        import java.util.ArrayList;

//the workout application class is the foundation of our application. this stores and loads all the information in the app
public class WorkoutApplication extends Application {
    private ArrayList<Workout> currentWorkout;
    public int workoutNum;
    private File direc;
    public int exerciseNum;
    public int runningWorkout;

    @Override
    public void onCreate() {
        super.onCreate();

        File dir = getFilesDir();
        File[] directoryListing = dir.listFiles();
        for(File file: directoryListing){
            Toast.makeText(getBaseContext(), file.getName(), Toast.LENGTH_SHORT).show();

            file.delete();
        }

        if(directoryListing.length != 0) {
            this.workoutNum = directoryListing.length;
        }
        else {
            this.workoutNum = 0;
        }

        if (null == currentWorkout) {
            this.currentWorkout = new ArrayList<Workout>();
        }
        this.currentWorkout = loadWorkouts();



    }

    public void setExerciseNum(int i ){
        this.exerciseNum= i;
    }

    public int getExerciseNum(){
        return this.exerciseNum;
    }

    public void addExerciseNum(){
        this.exerciseNum++;
    }


    public void setRunningWorkout(int i ){
        this.runningWorkout= i;
    }

    public int getRunningWorkout(){
        return this.runningWorkout;
    }




    public void addWorkoutNum(){
        this.workoutNum += 1;
    }

    public int getWorkoutNum(){
        int workCount = 0;
        for(Workout w: currentWorkout){
            workCount++;
        }

        this.workoutNum = workCount;
        return this.workoutNum;
    }

//this function returns all the workouts
    public ArrayList<Workout> loadWorkouts() {
        ArrayList<Workout> listWorkouts = new ArrayList<Workout>();
        int workCount = 0;
        String tbe = null;
        String tbstring;
        String workoutName = null;
        int exerciseCount = 0;
        String tbs = null;
        String exerciseName = null;
        String reps = null;
        String sets = null;
        BufferedReader bufferedReader;
        FileReader fileReader;
        int id = workoutNum;
        Exercise e;
        Workout w;
        try {
            File dir = getFilesDir();
            File[] directoryListing = dir.listFiles();
            int count = 0;
            if(directoryListing != null) {
                for (File child : directoryListing) {
                    ArrayList<Exercise> listExercise = new ArrayList<Exercise>();

                    if (!child.isDirectory()) {

                        FileInputStream fIn = openFileInput(child.getName());
                        BufferedReader bw = new BufferedReader(new InputStreamReader(fIn));
                        workoutName = bw.readLine();
                        tbe = bw.readLine();
                        String line = null;
                        int lineCounter =0;

                        w = new Workout(workoutName, tbe, workCount);
                        workCount++;
                        boolean notNull = true;

                        while(notNull) {
                            if((line =bw.readLine()) == null){
                                break;
                            }
                            else {
                                exerciseName = line;
                            }
                            sets = bw.readLine();
                            reps = bw.readLine();
                            tbs = bw.readLine();
                            e = new Exercise(exerciseName, sets, reps, tbs, count);
                            count++;
                            listExercise.add(e);
                        }



                        w.setCurrentExercise(listExercise);
//
                        listWorkouts.add(w);
                        bw.close();

                        }
//
                    }
            }
            else{

                return listWorkouts;
            }

        } catch (IOException z) {
            z.printStackTrace();
            Toast.makeText(getBaseContext(), "Exception", Toast.LENGTH_SHORT).show();

        }
        return listWorkouts;
    }

    public ArrayList<Workout> getCurrentWorkout() {
        return currentWorkout;
    }

    public void reloadWorkouts(){
        this.currentWorkout = loadWorkouts();
    }

    public void addWorkout(Workout w) {
        assert (null != w);
        currentWorkout.add(w);
    }

    public void setWorkoutNum(int id) {
        this.workoutNum = id;
    }

    public ArrayList<Exercise> getCurrentExercise(int i) throws IOException {

        return currentWorkout.get(i).getCurrentExercise();
    }
}