package com.example.seproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SeatingBooking extends AppCompatActivity {

    private ImageButton insideBtn, outsideBtn, seasideBtn, gardenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seating_booking);

        insideBtn = findViewById(R.id.insideBtn);
        outsideBtn = findViewById(R.id.outsideBtn);
        seasideBtn = findViewById(R.id.seasideBtn);
        gardenBtn = findViewById(R.id.gardenBtn);

        insideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                insideActivity();

            }
        });

        outsideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                outsideActivity();

            }
        });

        seasideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                seasideActivity();

            }
        });

        gardenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                gardenActivity();

            }
        });
    }

    public void insideActivity() {
        Intent intentHome = new Intent(this, ChooseSeater.class);
        startActivity(intentHome);
    }

    public void outsideActivity() {
        Intent intentHome = new Intent(this, ChooseSeater.class);
        startActivity(intentHome);
    }

    public void seasideActivity() {
        Intent intentHome = new Intent(this, ChooseSeater.class);
        startActivity(intentHome);
    }

    public void gardenActivity() {
        Intent intentHome = new Intent(this, ChooseSeater.class);
        startActivity(intentHome);
    }
}