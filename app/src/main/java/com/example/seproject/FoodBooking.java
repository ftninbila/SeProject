package com.example.seproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FoodBooking extends AppCompatActivity {

    private ImageButton breakfastButton, lunchButton, dinnerButtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_booking);

        breakfastButton=findViewById(R.id.breakfastButton);
        lunchButton=findViewById(R.id.lunchButton);
        dinnerButtton=findViewById(R.id.dinnerButton);

        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                breakfastActivity();
            }
        });

        lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                lunchActivity();
            }
        });

        dinnerButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dinnerActivity();
            }
        });
    }
    public void breakfastActivity(){
        Intent intentHome=new Intent(this,SeatingBooking.class);
        startActivity(intentHome);
    }

    public void lunchActivity(){
        Intent intentHome=new Intent(this,SeatingBooking.class);
        startActivity(intentHome);
    }

    public void dinnerActivity() {
        Intent intentHome = new Intent(this, SeatingBooking.class);
        startActivity(intentHome);
    }
}
