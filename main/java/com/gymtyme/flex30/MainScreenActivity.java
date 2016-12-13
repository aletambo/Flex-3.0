package com.gymtyme.flex30;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//the main screen activity is the first page that comes up when the application is opened
//this will load all the information stored in .txt files into lists to be accessed throughout the application
public class MainScreenActivity extends WorkoutManagerActivity {

    private Button startButton;
    private Button createButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        setUpViewsMain();
        ImageView myImageView = (ImageView) findViewById(R.id.imageView2);
        myImageView.setImageResource(R.drawable.flex333000);
    }

    //sets up the view of the main screen
    private void setUpViewsMain() {
        startButton = (Button)findViewById(R.id.start_button);
        createButton = (Button)findViewById(R.id.create_button);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, WorkoutListActivity.class);
                startActivity(intent);
            }
        });
        createButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, AddWorkoutActivity.class);
                startActivity(intent);
            }
        });
    }


}
